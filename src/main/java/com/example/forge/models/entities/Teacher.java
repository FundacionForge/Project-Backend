package com.example.forge.models.entities;

import com.example.forge.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="teachers")
@Getter
@Setter
public class Teacher extends BaseEntity {

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
	@Email
	@Column(unique = true)
	private String email;
	
	@NotNull
	@NotBlank
	private String address;
	
	@NotNull
	@NotBlank
	@Size(min = 8)
	private String qualification;
	
	@NotNull
	@NotBlank
	@Column(name = "phone_number", unique = true)
	private String phoneNumber;
	
	public Teacher() {
	}
	
	public Teacher (String dni, String name, String lastName, String email, String address, String qualification,
			String phoneNumber) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.qualification = qualification;
		this.phoneNumber = phoneNumber;
	}
	
	public void setTeacher(String name, String lastName, String email, String address, String qualification,
			String phoneNumber) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.qualification = qualification;
		this.phoneNumber = phoneNumber;
	}
}
