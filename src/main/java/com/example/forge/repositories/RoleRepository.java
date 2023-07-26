package com.example.forge.repositories;

import java.util.Optional;
import com.example.forge.models.entities.Role;
import com.example.forge.repositorybase.RepositoryBase;

public interface RoleRepository extends RepositoryBase<Role> {
  Optional<Role> findByName(String user);
}
