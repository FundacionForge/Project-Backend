package com.example.forge.models.entities;

import java.util.List;

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
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="students")
@Getter
@Setter
@NoArgsConstructor
public class Student extends BaseEntity {
	@NotNull
	@NotBlank
	@Column(unique = true)
	private String dni;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "mother_last_name")
	private String motherLastName;

	@NotNull
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	@Column(name = "phone_number", unique = true)
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
  private List<Course> courses;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "student_degree",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "degree_id")
  )
  private List<Degree> degrees;

  @ManyToOne
  @JoinColumn(name = "shift_id")
  private Shift shift;
}
