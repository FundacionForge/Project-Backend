package com.example.forge.app.domain.entities;

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
@Table(name="degrees")
@Getter
@Setter
@NoArgsConstructor
public class DegreeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	@Column(name = "assigned_room")
	private String assignedRoom;

	@NotNull
	@NotBlank
	@Column(name = "academic_level")
	private String academicLevel;

  @ManyToMany(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinTable(
      name = "degree_teacher",
      joinColumns = @JoinColumn(name = "degree_id"),
      inverseJoinColumns = @JoinColumn(name = "teacher_id")
  )
  private Set<TeacherEntity> teachers = new HashSet<>();

  @OneToMany(mappedBy = "degrees")
  @JsonIgnore
  private Set<StudentEntity> students = new HashSet<>();
}
