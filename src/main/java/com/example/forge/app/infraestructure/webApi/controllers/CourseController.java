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

import com.example.forge.app.application.services.CourseService;
import com.example.forge.app.domain.entities.CourseEntity;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
	private CourseService courseService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllStudent() {
		List<CourseEntity> courses = courseService.getAll();
		HttpStatus responseStatus = courses.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		String responseMessage = courses.isEmpty() ? "No existen cursos" : "Cursos encontrados";

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", courses);

		return ResponseEntity.status(responseStatus).body(response);
	}

	@PostMapping
	public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseEntity course) {
		CourseEntity createdCourse = courseService.create(course);
		HttpStatus responseStatus = createdCourse != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

		return ResponseEntity.status(responseStatus)
				.body(createdCourse);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getCourse(@PathVariable Long id) {
		CourseEntity course = courseService.getById(id);

		if (course != null) {
			return ResponseEntity.ok().body(course);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se encontró el curso con ID %d", id));
			msg.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseEntity course) {
		CourseEntity updatedCourse = courseService.updateById(id, course);

		if (updatedCourse != null) {
			return ResponseEntity.ok().body(updatedCourse);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo actualizar el curso con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
		boolean deleted = courseService.deleteById(id);

		if (deleted) {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.TRUE);
			msg.put("msg", String.format("Curso con ID %d eliminado exitosamente", id));
			msg.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(msg);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo eliminar el curso con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
}
