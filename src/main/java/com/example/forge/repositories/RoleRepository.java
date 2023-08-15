package com.example.forge.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.example.forge.models.entities.Role;


public interface RoleRepository extends CrudRepository<Role, Long> {
  Optional<Role> findByName(String string);
}
