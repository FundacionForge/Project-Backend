package com.example.forge.models.entities;

import com.example.forge.models.BaseEntity;

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
@Table(name="degrees")
@Getter
@Setter
@NoArgsConstructor
public class Degree extends BaseEntity {
	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	private String name;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "assigned_room")
	private String assignedRoom;

	@NotNull
	@NotBlank
	@Size(min = 4, max = 15)
	@Column(name = "academic_level")
	private String academicLevel;
}
