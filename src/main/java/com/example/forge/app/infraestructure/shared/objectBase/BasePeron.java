package com.example.forge.app.infraestructure.shared.objectBase;

import com.example.forge.app.infraestructure.shared.abstractBase.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BasePeron extends BaseEntity {
	@NotBlank(message = "El DNI no puede estar en blanco")
	@Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
	@Column(unique = true)
	private String dni;

	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(min = 3, max = 10, message = "El nombre debe tener entre 3 y 10 caracteres")
	private String name;

	@NotBlank(message = "El apellido no puede estar en blanco")
	@Size(min = 3, max = 10, message = "El apellido debe tener entre 3 y 10 caracteres")
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "El apellido materno no puede estar en blanco")
	@Size(min = 3, max = 10, message = "El apellido materno debe tener entre 3 y 10 caracteres")
	@Column(name = "mother_last_name")
	private String motherLastName;

	@NotBlank(message = "El email no puede estar en blanco")
	@Email(message = "El email debe ser válido")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "El número de teléfono no puede estar en blanco")
	@Size(min = 9, max = 9, message = "El número de teléfono debe tener 9 dígitos")
	private String phoneNumber;

	@NotBlank(message = "La dirección no puede estar en blanco")
	private String address;
}
