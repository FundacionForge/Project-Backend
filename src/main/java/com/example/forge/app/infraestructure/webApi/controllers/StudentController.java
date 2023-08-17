package com.example.forge.app.infraestructure.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.app.application.services.StudentService;
import com.example.forge.app.domain.entities.StudentEntity;

@RestController
@RequestMapping("student")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping
  public List<StudentEntity> getAllStudent() {
    return studentService.getAll();
  }

  @PostMapping
  public StudentEntity createStudent(@RequestBody StudentEntity student) {
    return studentService.create(student);
  }

  @GetMapping("{id}")
  public StudentEntity getStudent(@PathVariable Long id) {
    return studentService.getById(id);
  }

  @PutMapping("{id}")
  public StudentEntity updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
    return studentService.updateById(id, student);
  }

  @DeleteMapping("{id}")
  public boolean deleteStudent(@PathVariable Long id) {
    return studentService.deleteById(id);
  }
}
