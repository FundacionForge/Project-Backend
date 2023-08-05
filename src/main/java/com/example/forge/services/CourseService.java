package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Course;
import com.example.forge.repositories.CourseRepository;

@Service
public class CourseService {
  @Autowired
  private CourseRepository repository;

  public Course create(Course course) {
    return repository.save(course);
  }

  public List<Course> getAll(){
    return (List<Course>) repository.findAll();
  }

  public Optional<Course> getById(Long id){
    return repository.findById(id);
  }

  public Course updateById(Long id, Course updatedCourse){
    Optional<Course> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      Course course = optionalCourse.get();
      course.setName(updatedCourse.getName());
      course.setDescription(updatedCourse.getDescription());
      course.setImage(updatedCourse.getImage());
      return repository.save(course);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<Course> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
