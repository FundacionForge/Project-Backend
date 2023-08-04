package com.example.forge.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	private String description;

	private String image;

  @ManyToMany(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinTable(
    name = "course_student",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private Set<Student> students = new HashSet<>();

  @OneToMany(mappedBy = "courses")
  @JsonIgnore
  private Set<Teacher> teachers = new HashSet<>();
}
