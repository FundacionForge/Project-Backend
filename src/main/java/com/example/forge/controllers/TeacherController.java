package com.example.forge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.models.entities.Teacher;
import com.example.forge.services.TeacherService;

@RestController
@RequestMapping("teacher")
public class TeacherController {

	@Autowired
	private TeacherService service;

	@GetMapping
  public List<Teacher> getAllTeacher() {
    return service.getAll();
  }

	@PostMapping
	public Teacher createTeacher(@RequestBody Teacher teacher) {
		return service.create(teacher);
	}

	@GetMapping("{id}")
	public Teacher getTeacher(@PathVariable Long id) {
		Teacher teacher = service.getById(id);
		return teacher;
	}

  @PutMapping("{id}")
  public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherData) {
  	Teacher updateTeacher = service.updateById(id, teacherData);
    return updateTeacher;
  }

  @DeleteMapping("{id}")
  public void deleteTeacher(@PathVariable Long id) {
  	service.deleteById(id);
  }
}
