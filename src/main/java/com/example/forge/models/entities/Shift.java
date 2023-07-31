package com.example.forge.models.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

	@NotNull
	@NotBlank
	private String name;

  @OneToMany(mappedBy = "shift")
  private Set<Student> students = new HashSet<>();

  @OneToMany(mappedBy = "shift")
  private Set<Teacher> teachers = new HashSet<>();
}
