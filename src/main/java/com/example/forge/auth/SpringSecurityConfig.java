package com.example.forge.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.forge.auth.filters.JwtAuthenticationFilter;
import com.example.forge.auth.filters.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;
  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  };
  @Bean
  AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(authRules -> authRules
      .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
      .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
      // .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("USER", "ADMIN")

      .requestMatchers("/api/student/**").hasRole("ADMIN")
      // .requestMatchers("/api/users/**").hasRole("ADMIN")

      .requestMatchers(HttpMethod.GET, "/api/teacher/**").permitAll()
      .requestMatchers(HttpMethod.PUT, "/api/teacher/**").permitAll()
      .requestMatchers(HttpMethod.DELETE, "/api/teacher/**").permitAll()
      .requestMatchers(HttpMethod.POST, "/api/teacher/**").permitAll()

      // .requestMatchers(HttpMethod.GET, "/api/student/**").hasAnyRole("USER", "ADMIN")
      // .requestMatchers(HttpMethod.PUT, "/api/student/**").permitAll()
      // .requestMatchers(HttpMethod.DELETE, "/api/student/**").permitAll()
      // .requestMatchers(HttpMethod.POST, "/api/student/**").permitAll()

      .requestMatchers(HttpMethod.GET, "/api/course/**").permitAll()
      .requestMatchers(HttpMethod.PUT, "/api/course/**").permitAll()
      .requestMatchers(HttpMethod.DELETE, "/api/course/**").permitAll()
      .requestMatchers(HttpMethod.POST, "/api/course/**").permitAll()
      // .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
      // .requestMatchers(HttpMethod.DELETE, "api/users/{id}").hasRole("ADMIN")
      // .requestMatchers(HttpMethod.PUT, "api/users/{id}").hasRole("ADMIN")
      .anyRequest().authenticated())
      .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
      .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
      .csrf(config -> config.disable())
      .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOriginPatterns(Arrays.asList("*"));
      // config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
      config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
      config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
      config.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
      FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
        new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
