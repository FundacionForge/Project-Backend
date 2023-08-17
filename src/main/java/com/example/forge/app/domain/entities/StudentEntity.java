package com.example.forge.app.domain.entities;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends BaseEntity {
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
	@Column(name = "mother_last_name")
	private String motherLastName;

	@NotNull
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@NotBlank
	private String address;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "course_student",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<CourseEntity> courses = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "degree_id")
  private DegreeEntity degrees;

  @ManyToOne
  @JoinColumn(name = "shift_id")
  private ShiftEntity shifts;
}
