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

import com.example.forge.app.application.services.StudentService;
import com.example.forge.app.domain.entities.StudentEntity;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping
	public ResponseEntity<List<StudentEntity>> getAllStudent() {
		List<StudentEntity> students = studentService.getAll();
		HttpStatus responseStatus = students.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		String responseMessage = students.isEmpty() ? "No existen estudiantes" : "Estudiantes encontrados";

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", students);

		return ResponseEntity.status(responseStatus).body(students);
	}

	@PostMapping
	public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity student) {
		StudentEntity createdStudent = studentService.create(student);
		HttpStatus responseStatus = createdStudent != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

		return ResponseEntity.status(responseStatus)
				.body(createdStudent);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getStudent(@PathVariable Long id) {
		StudentEntity student = studentService.getById(id);

		if (student != null) {
			return ResponseEntity.ok().body(student);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se encontró el estudiante con ID %d", id));
			msg.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody StudentEntity student) {
		StudentEntity updatedStudent = studentService.updateById(id, student);

		if (updatedStudent != null) {
			return ResponseEntity.ok().body(updatedStudent);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo actualizar el estudiante con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
		boolean deleted = studentService.deleteById(id);

		if (deleted) {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.TRUE);
			msg.put("msg", String.format("Estudiante con ID %d eliminado exitosamente", id));
			msg.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(msg);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo eliminar el estudiante con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
}
