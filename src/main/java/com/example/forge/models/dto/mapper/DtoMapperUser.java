package com.example.forge.models.dto.mapper;

import com.example.forge.models.dto.UserDto;
import com.example.forge.models.entities.User;

public class DtoMapperUser {
  private User user;
  private DtoMapperUser(){ }

  public static DtoMapperUser builder() {
    return new DtoMapperUser();
  }

  public DtoMapperUser setUser(User user) {
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
