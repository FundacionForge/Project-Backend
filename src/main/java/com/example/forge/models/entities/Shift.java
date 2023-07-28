package com.example.forge.models.entities;

import com.example.forge.models.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="shifts")
@Getter
@Setter
@NoArgsConstructor
public class Shift extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;
}
