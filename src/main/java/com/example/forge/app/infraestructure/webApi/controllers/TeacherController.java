package com.example.forge.app.infraestructure.webApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.app.application.services.TeacherService;
import com.example.forge.app.domain.entities.TeacherEntity;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllTeachers() {
		List<TeacherEntity> teachers = teacherService.getAll();
		HttpStatus responseStatus = teachers.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		String responseMessage = teachers.isEmpty() ? "No existen profesores" : "Profesores encontrados";

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", teachers);

		return ResponseEntity.status(responseStatus).body(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createTeacher(@RequestBody TeacherEntity teacher) {
		TeacherEntity createdTeacher = teacherService.create(teacher);

		if (createdTeacher != null) {
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("msg", "Profesor creado exitosamente");
			response.put("data", createdTeacher);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("msg", "No se pudo crear el profesor");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getTeacher(@PathVariable Long id) {
		TeacherEntity teacher = teacherService.getById(id);

		if (teacher != null) {
			return ResponseEntity.ok().body(teacher);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se encontró el profesor con ID %d", id));
			msg.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Map<String, Object>> updateTeacher(
			@PathVariable Long id, @RequestBody TeacherEntity teacherData) {
		TeacherEntity updatedTeacher = teacherService.updateById(id, teacherData);

		if (updatedTeacher != null) {
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("msg", "Profesor actualizado exitosamente");
			response.put("data", updatedTeacher);

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("msg", "No se pudo actualizar el profesor");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Map<String, Object>> deleteTeacher(@PathVariable Long id) {
		boolean deleted = teacherService.deleteById(id);

		if (deleted) {
			Map<String, Object> response = new HashMap<>();
			response.put("success", true);
			response.put("msg", "Profesor eliminado exitosamente");

			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("success", false);
			response.put("msg", "No se pudo eliminar el profesor");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}
