package com.example.forge.models.entities;

import java.util.List;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="courses")
@Getter
@Setter
@NoArgsConstructor
public class Course extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 25)
	private String description;

	@Size(min = 10)
	private String image;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "course_student",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<Student> students;

  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;
}
