package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import com.example.forge.models.dto.UserDto;
import com.example.forge.models.entities.User;
import com.example.forge.models.request.UserRequest;

public interface UserService {
  List<UserDto> findAll();
  Optional<UserDto> findById(Long id);
  UserDto save(User user);
  Optional<UserDto> update(UserRequest user, Long id);
  void remove(Long id);
}
