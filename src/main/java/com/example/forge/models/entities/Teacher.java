package com.example.forge.models.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@Size(min = 3, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 15)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	private String address;

	@NotNull
	@NotBlank
	@Size(min = 2)
	private String qualification;

	@NotNull
	@NotBlank
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "degree_teacher",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "degree_id")
  )
  private List<Degree> degrees;

  @ManyToOne
  @JoinColumn(name = "shift_id")
  private Shift shift;

  @OneToMany(mappedBy = "teacher")
  private Set<Course> courses = new HashSet<>();
}
