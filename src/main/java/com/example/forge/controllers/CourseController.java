package com.example.forge.controllers;

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

import com.example.forge.models.entities.Course;
import com.example.forge.services.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public List<Course> getAllStudent(){
        return service.getAll();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return service.create(course);
    }

    @GetMapping("{id}")
    public Optional<Course> getCourse(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course){
        return service.updateById(id, course);
    }

    @DeleteMapping("{id}")
    public boolean deleteStudent(@PathVariable Long id){
        return service.deleteById(id);
    }
}
