package com.example.forge.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import com.example.forge.models.entities.User;
import com.example.forge.repositorybase.RepositoryBase;

public interface UserRepository extends RepositoryBase<User>{
  Optional<User> findByUsername(String username);

  @Query("select u from User u where u.username=?1")
  Optional<User> getUserByUsername(String username);
}
