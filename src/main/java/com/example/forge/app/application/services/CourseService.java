package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.CourseEntity;
import com.example.forge.app.domain.repositories.CourseRepository;

@Service
public class CourseService {
  @Autowired
  private CourseRepository repository;

  public CourseEntity create(CourseEntity course) {
    return repository.save(course);
  }

  public List<CourseEntity> getAll(){
    return (List<CourseEntity>) repository.findAll();
  }

  public Optional<CourseEntity> getById(Long id){
    return repository.findById(id);
  }

  public CourseEntity updateById(Long id, CourseEntity updatedCourse){
    Optional<CourseEntity> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      CourseEntity course = optionalCourse.get();
      course.setName(updatedCourse.getName());
      course.setDescription(updatedCourse.getDescription());
      course.setImage(updatedCourse.getImage());
      return repository.save(course);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<CourseEntity> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
