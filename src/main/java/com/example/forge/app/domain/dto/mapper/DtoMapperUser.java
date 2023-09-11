package com.example.forge.app.domain.dto.mapper;

import com.example.forge.app.domain.dto.UserDto;
import com.example.forge.app.domain.entities.UserEntity;

public class DtoMapperUser {
  private UserEntity user;
  private DtoMapperUser(){ }

  public static DtoMapperUser builder() {
    return new DtoMapperUser();
  }

  public DtoMapperUser setUser(UserEntity user) {
    this.user = user;
    return this;
  }

  public UserDto build() {
    if (user == null) {
      throw new RuntimeException("Debe pasar el entity user!");
    }
    UserDto userDto = new UserDto(this.user.getId(), this.user.getUsername(), this.user.getEmail());
    return userDto;
  }
}
