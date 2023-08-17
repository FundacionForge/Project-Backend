package com.example.forge.app.infraestructure.shared.objectBase;

import com.example.forge.app.infraestructure.shared.abstractBase.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BasePeron extends BaseEntity {
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
	@Column(unique = true)
	private String email;

	@NotNull
	@NotBlank
	private String phoneNumber;

	@NotNull
	@NotBlank
	private String address;
}
