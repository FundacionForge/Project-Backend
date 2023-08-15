package com.example.forge.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.forge.models.entities.Student;
import com.example.forge.services.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
  @Autowired
  private StudentService service;

  @GetMapping
  public List<Student> getAllStudent() {
    return service.getAll();
  }

  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return service.create(student);
  }

  @GetMapping("{id}")
  public Optional<Student> getStudent(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("{id}")
  public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
    return service.updateById(id, student);
  }

  @DeleteMapping("{id}")
  public boolean deleteStudent(@PathVariable Long id) {
    return service.deleteById(id);
  }

  @GetMapping("/page/{pageNumber}")
  public Page<Student> getStudentsPage(@PathVariable int pageNumber) {
    return service.studentPage(pageNumber);
  }
}
