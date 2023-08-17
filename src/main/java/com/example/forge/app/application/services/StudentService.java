package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.StudentEntity;
import com.example.forge.app.domain.repositories.StudentRepository;

@Service
public class StudentService {
  @Autowired
  private StudentRepository repository;

  public StudentEntity create(StudentEntity student) {
    return repository.save(student);
  }

  public List<StudentEntity> getAll() {
    return (List<StudentEntity>) repository.findAll();
  }

  public Optional<StudentEntity> getById(Long id) {
    return repository.findById(id);
  }

  public StudentEntity updateById(Long id, StudentEntity updatedStudent) {
    Optional<StudentEntity> optionalStudent = repository.findById(id);
    if (optionalStudent.isPresent()) {
      StudentEntity student = optionalStudent.get();
      student.setName(updatedStudent.getName());
      student.setLastName(updatedStudent.getLastName());
      student.setMotherLastName(updatedStudent.getMotherLastName());
      student.setEmail(updatedStudent.getEmail());
      student.setPhoneNumber(updatedStudent.getPhoneNumber());
      student.setAddress(updatedStudent.getAddress());
      return repository.save(student);
    }
     return null;
  }

  public boolean deleteById(Long id) {
    Optional<StudentEntity> optionalStudent = repository.findById(id);
    if (optionalStudent.isPresent()) {
      repository.delete(optionalStudent.get());
      ;
      return true;
    }
    return false;
  }
}
