package com.example.forge.app.infraestructure.webApi.controllers;

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

import com.example.forge.app.application.services.TeacherService;
import com.example.forge.app.domain.entities.TeacherEntity;

@RestController
@RequestMapping("teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public Page<TeacherEntity> getAllTeacher(@RequestParam(value = "page", required = false) Integer pageNumber,
			@RequestParam(value = "size", required = false) Integer size) {
		return teacherService.teacherPerPage(pageNumber, size);
	}

	@PostMapping
	public TeacherEntity createTeacher(@RequestBody TeacherEntity teacher) {
		return teacherService.create(teacher);
	}

	@GetMapping("{id}")
	public TeacherEntity getTeacher(@PathVariable Long id) {
		TeacherEntity teacher = teacherService.getById(id);
		return teacher;
	}

	@PutMapping("{id}")
	public TeacherEntity updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacherData) {
		TeacherEntity updateTeacher = teacherService.updateById(id, teacherData);
		return updateTeacher;
	}

	@DeleteMapping("{id}")
	public void deleteTeacher(@PathVariable Long id) {
		teacherService.deleteById(id);
	}

}
