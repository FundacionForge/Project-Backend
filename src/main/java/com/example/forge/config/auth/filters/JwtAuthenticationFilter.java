package com.example.forge.config.auth.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.forge.app.domain.entities.UserEntity;
import com.example.forge.config.auth.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
        UserEntity user = null;
        String username = null;
        String password = null;

        try {
          user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
          username = user.getUsername();
          password = user.getPassword();
        } catch (IOException e) {
          e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal())
          .getUsername();

        Date expirationDate = new Date(System.currentTimeMillis() + 30L * 24L * 60L * 60L * 1000L);
        String token = Jwts.builder()
          .setSubject(username)
          .setIssuedAt(new Date())
          .setExpiration(expirationDate)
          .signWith(TokenJwtConfig.SECRET_KEY)
          .compact();

        response.addHeader(TokenJwtConfig.HEADER_AUTHORIZATION, TokenJwtConfig.PREFIX_TOKEN +token);

        Map<String, Object> body = new HashMap<>();
        body.put("token", token);
        body.put("message", String.format("Hola %s, has iniciado sesion con exito", username));
        body.put("username", username);
        String responseBody = new ObjectMapper().writeValueAsString(body);
        response.getWriter().write(responseBody);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(TokenJwtConfig.CONTENT_TYPE_JSON);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error de autenticación: Nombre de usuario o contraseña incorrectos");
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(TokenJwtConfig.CONTENT_TYPE_JSON);
  }
}
