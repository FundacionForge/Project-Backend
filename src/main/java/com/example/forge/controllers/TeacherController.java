package com.example.forge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.models.entities.Teacher;
import com.example.forge.services.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

	@Autowired
	private TeacherService service;

	@GetMapping
	public Page<Teacher> getAllTeacher(@RequestParam (value="page", required=false) Integer pageNumber,
			@RequestParam (value="size", required=false) Integer size) {
		return service.teacherPerPage(pageNumber, size);
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
