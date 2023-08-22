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

import com.example.forge.app.application.services.DegreeService;
import com.example.forge.app.domain.entities.DegreeEntity;

@RestController
@RequestMapping("/api/degree")
public class DegreeController {
	@Autowired
	private DegreeService degreeService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllDegrees() {
		List<DegreeEntity> degrees = degreeService.getAll();
		HttpStatus responseStatus = degrees.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		String responseMessage = degrees.isEmpty() ? "No existen grados" : "Grados encontrados";

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", degrees);

		return ResponseEntity.status(responseStatus).body(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createDegree(@RequestBody DegreeEntity degree) {
		DegreeEntity createdDegree = degreeService.create(degree);

		Map<String, Object> response = new HashMap<>();
		if (createdDegree != null) {
			response.put("success", Boolean.TRUE);
			response.put("data", createdDegree);
			response.put("status", HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("msg", "Error al crear el grado");
			response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Map<String, Object>> getDegree(@PathVariable Long id) {
		DegreeEntity degree = degreeService.getById(id);

		Map<String, Object> response = new HashMap<>();
		if (degree != null) {
			response.put("success", Boolean.TRUE);
			response.put("data", degree);
			response.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(response);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("msg", String.format("No se encontró el grado con ID %d", id));
			response.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Map<String, Object>> updateDegree(@PathVariable Long id, @RequestBody DegreeEntity degree) {
		DegreeEntity updatedDegree = degreeService.updateById(id, degree);

		Map<String, Object> response = new HashMap<>();
		if (updatedDegree != null) {
			response.put("success", Boolean.TRUE);
			response.put("data", updatedDegree);
			response.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(response);
		} else {
			response.put("success", Boolean.FALSE);
			response.put("msg", String.format("No se encontró el grado con ID %d", id));
			response.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteDegree(@PathVariable Long id) {
		boolean deleted = degreeService.deleteById(id);

		if (deleted) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
