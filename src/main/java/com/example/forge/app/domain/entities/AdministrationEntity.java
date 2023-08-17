package com.example.forge.app.domain.entities;

import com.example.forge.app.infraestructure.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="administrations")
@Getter
@Setter
@NoArgsConstructor
public class AdministrationEntity extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "last_name")
	private String lastName;
}
