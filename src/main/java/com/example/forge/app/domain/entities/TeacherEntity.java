package com.example.forge.app.domain.entities;

import java.util.HashSet;
import java.util.Set;

import com.example.forge.app.infraestructure.shared.objectBase.BasePeron;

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
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
public class TeacherEntity extends BasePeron {
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "degree_teacher", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "degree_id"))
	private Set<DegreeEntity> degrees = new HashSet<DegreeEntity>();

	@ManyToOne
	@JoinColumn(name = "shift_id")
	private ShiftEntity shifts;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity courses;

	@ManyToOne
	@JoinColumn(name = "qualification_id")
	private QualificationEntity qualification;
}
