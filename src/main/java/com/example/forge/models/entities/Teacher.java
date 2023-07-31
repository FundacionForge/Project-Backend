package com.example.forge.models.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="teachers")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends BaseEntity {
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String dni;

	@NotNull
	@NotBlank
	private String name;

	@NotNull
	@NotBlank
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@NotBlank
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	private String address;

	@NotNull
	@NotBlank
	private String qualification;

	@NotNull
	@NotBlank
	private String phoneNumber;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "degree_teacher",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "degree_id")
  )
  private Set<Degree> degrees = new HashSet<Degree>();

  @ManyToOne
  @JoinColumn(name = "shift_id")
  private Shift shift;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;
}
