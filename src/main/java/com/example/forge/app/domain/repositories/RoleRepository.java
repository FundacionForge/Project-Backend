package com.example.forge.app.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.forge.app.domain.entities.RoleEntity;


public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByName(String string);
}
