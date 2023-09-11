package com.example.forge.app.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.forge.app.domain.dto.UserDto;
import com.example.forge.app.domain.dto.mapper.DtoMapperUser;
import com.example.forge.app.domain.entities.RoleEntity;
import com.example.forge.app.domain.entities.UserEntity;
import com.example.forge.app.domain.repositories.RoleRepository;
import com.example.forge.app.domain.repositories.UserRepository;
import com.example.forge.app.domain.response.UserResponse;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public List<UserDto> findAll() {
    List<UserEntity> users = (List<UserEntity>) repository.findAll();
    return users
      .stream()
      .map(u -> DtoMapperUser.builder().setUser(u).build())
      .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public Optional<UserDto> findById(Long id) {
    return repository.findById(id).map(u -> DtoMapperUser
    .builder()
    .setUser(u)
    .build());
  }

  @Transactional
  public UserDto save(UserEntity user) {
    String passwordBCrypt =  passwordEncoder.encode(user.getPassword());
    user.setPassword(passwordBCrypt);

    Optional<RoleEntity> o = roleRepository.findByName("ROLE_USER");
    List<RoleEntity> roles = new ArrayList<>();
    if(o.isPresent()) {
      roles.add(o.orElseThrow());
    }
    user.setRoles(roles);
    return DtoMapperUser.builder().setUser(repository.save(user)).build();
  }

  @Transactional
  public Optional<UserDto> update(UserResponse user, Long id) {
    Optional<UserEntity> o = repository.findById(id);
    UserEntity userOptional = null;
      if(o.isPresent()){
        UserEntity userDb = o.orElseThrow();
        userDb.setUsername(user.getUsername());
        userDb.setEmail(user.getEmail());
        userOptional = repository.save(userDb);
    }
    return Optional.ofNullable(DtoMapperUser.builder().setUser(userOptional).build());
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }
}
