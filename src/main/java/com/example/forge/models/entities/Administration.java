package com.example.forge.models.entities;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name="administrations")
@Getter
@Setter
public class Administration extends BaseEntity {
	
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
