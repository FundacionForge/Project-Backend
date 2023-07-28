package com.example.forge.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.forge.models.entities.Course;

public interface CourseRepository extends CrudRepository<Course,Long> {}
