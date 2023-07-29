package com.example.forge.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

  @OneToMany(mappedBy = "shift")
  private Set<Student> students = new HashSet<>();

  @OneToMany(mappedBy = "shift")
  private Set<Teacher> teachers = new HashSet<>();
}
