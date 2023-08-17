package com.example.forge.config.auth.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.forge.config.auth.TokenJwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {
	public JwtValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(TokenJwtConfig.HEADER_AUTHORIZATION);
		if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {
			chain.doFilter(request, response);
			return;
		}
		String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");
		try {
			Claims claims = Jwts.parserBuilder()
					.setSigningKey(TokenJwtConfig.SECRET_KEY)
					.build()
					.parseClaimsJws(token)
					.getBody();

			String username = claims.getSubject();

			Collection<GrantedAuthority> authorities = new ArrayList<>();

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		} catch (JwtException e) {
			Map<String, String> body = new HashMap<>();
			body.put("error", e.getMessage());
			body.put("message", "El token JWT no es valido!");
			response.getWriter().write(new ObjectMapper().writeValueAsString(body));
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType(TokenJwtConfig.CONTENT_TYPE_JSON);
		}
	}
}
