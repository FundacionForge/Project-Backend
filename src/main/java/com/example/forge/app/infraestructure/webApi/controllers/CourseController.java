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

import com.example.forge.app.application.services.CourseService;
import com.example.forge.app.domain.entities.CourseEntity;

@RestController
@RequestMapping("course")
public class CourseController {
  @Autowired
  private CourseService service;

  @GetMapping
  public List<CourseEntity> getAllStudent(){
    return service.getAll();
  }

  @PostMapping
  public CourseEntity createCourse(@RequestBody CourseEntity course){
    return service.create(course);
  }

  @GetMapping("{id}")
  public Optional<CourseEntity> getCourse(@PathVariable Long id){
    return service.getById(id);
  }

  @PutMapping("{id}")
  public CourseEntity updateCourse(@PathVariable Long id, @RequestBody CourseEntity course){
    return service.updateById(id, course);
  }

  @DeleteMapping("{id}")
  public boolean deleteStudent(@PathVariable Long id){
    return service.deleteById(id);
  }
}
