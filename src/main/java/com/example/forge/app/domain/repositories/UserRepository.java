package com.example.forge.app.domain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.forge.app.domain.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
  Optional<UserEntity> findByUsername(String username);

  @Query("select u from UserEntity u where u.username=?1")
  Optional<UserEntity> getUserByUsername(String username);

  void findByEmail(String email);
}
