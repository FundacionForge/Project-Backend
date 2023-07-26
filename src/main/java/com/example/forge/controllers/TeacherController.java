package com.example.forge.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.forge.models.entities.Teacher;
import com.example.forge.services.TeacherService;

@RestController
public class TeacherController {
	
	private final TeacherService teacherService;
	
	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@RequestMapping("/api/teacher")
    public List<Teacher> index() {
        return teacherService.findAll();
    }
	
	@PostMapping("/api/teacher")
	public Teacher create(@RequestParam("dni") String dni, @RequestParam("name") String name, 
						  @RequestParam("lastName") String lastName, @RequestParam("email") String email,
						  @RequestParam("address") String address, @RequestParam("qualification") String qualification,
						  @RequestParam("phoneNumber") String phoneNumber) {
		Teacher teacher = new Teacher(dni, name, lastName, email, address, qualification, phoneNumber);
		return teacherService.save(teacher);
	}
	
	@RequestMapping("/api/teacher/{id}")
	public Teacher show(@PathVariable("id") Long id) {
		Teacher teacher = teacherService.findById(id);
		return teacher;
	}
	
    @PutMapping("/api/teacher/{id}")
    public Teacher update(@PathVariable("id") Long id, @RequestParam("name") String name, 
			  @RequestParam("lastName") String lastName, @RequestParam("email") String email,
			  @RequestParam("address") String address, @RequestParam("qualification") String qualification,
			  @RequestParam("phoneNumber") String phoneNumber) {
    	Teacher teacher = teacherService.findById(id);
    	teacher.setTeacher(name, lastName, email, address, qualification, phoneNumber);
    	teacherService.save(teacher);
        return teacher;
    }
    
    @DeleteMapping("/api/teacher/{id}")
    public void destroy(@PathVariable("id") Long id) {
    	teacherService.deleteById(id);
    }
}
