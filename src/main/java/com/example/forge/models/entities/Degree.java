package com.example.forge.models.entities;

import java.util.List;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="degrees")
@Getter
@Setter
@NoArgsConstructor
public class Degree extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "assigned_room")
	private String assignedRoom;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "academic_level")
	private String academicLevel;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "student_degree",
    joinColumns = @JoinColumn(name = "degree_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<Student> students;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "degree_teacher",
    joinColumns = @JoinColumn(name = "degree_id"),
    inverseJoinColumns = @JoinColumn(name = "teacher_id")
  )
  private List<Teacher> teachers;
}
