package com.example.forge.models.entities.manyToMany;

import com.example.forge.models.BaseEntity;
import com.example.forge.models.entities.Degree;
import com.example.forge.models.entities.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="student_degree")
@Getter
@Setter
@NoArgsConstructor
public class StudentDegree extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "degree_id")
  private Degree degree;
}
