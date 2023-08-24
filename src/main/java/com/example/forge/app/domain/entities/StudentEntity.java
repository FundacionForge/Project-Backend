package com.example.forge.app.domain.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.forge.app.infraestructure.shared.objectBase.BasePeron;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends BasePeron {
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<CourseEntity> courses = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "degree_id")
	private DegreeEntity degrees;

	@ManyToOne
	@JoinColumn(name = "shift_id")
	private ShiftEntity shifts;
}
