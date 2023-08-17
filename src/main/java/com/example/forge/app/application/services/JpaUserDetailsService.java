package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forge.app.domain.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepository repository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<com.example.forge.app.domain.entities.UserEntity> o = repository.getUserByUsername(username);

    if(!o.isPresent()) {
      throw new UsernameNotFoundException(String.format("Username %s no existe en el sistema", username));
    }
    com.example.forge.app.domain.entities.UserEntity user = o.orElseThrow();

    List<GrantedAuthority> authorities = user.getRoles()
      .stream()
      .map(r -> new SimpleGrantedAuthority(r.getName()))
      .collect(Collectors.toList());

    return new User(
      user.getUsername(),
      user.getPassword()  ,
      true,
      true,
      true,
      true,
      authorities
    );
  }
}
