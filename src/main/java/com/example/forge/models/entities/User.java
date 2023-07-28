package com.example.forge.models.entities;

import java.util.List;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
  @NotNull
  @NotBlank
  @Column(unique = true)

  @NotNull
  @NotBlank
  @Size(min = 4, max = 8)
  private String username;

  @NotNull
  @NotBlank
  private String password;

  @NotNull
  @NotBlank
  @Email
  @Column(unique = true)
  private String email;

  @ManyToMany
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"),
    uniqueConstraints = { @UniqueConstraint(columnNames = {"user_id", "role_id"}) }
  )
  private List<Role> roles;
}
