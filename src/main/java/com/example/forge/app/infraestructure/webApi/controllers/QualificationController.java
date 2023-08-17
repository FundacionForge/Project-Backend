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

import com.example.forge.app.application.services.QualificationService;
import com.example.forge.app.domain.entities.QualificationEntity;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {
	@Autowired
	private QualificationService quantificationService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllQualification() {
		List<QualificationEntity> qualifications = quantificationService.getAll();
		HttpStatus responseStatus = qualifications.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
		String responseMessage = qualifications.isEmpty() ? "No existen calificaciones" : "Calificaciones encontrados";

		Map<String, Object> response = new HashMap<>();
		response.put("success", responseStatus == HttpStatus.OK);
		response.put("msg", responseMessage);
		response.put("data", qualifications);
		return ResponseEntity.status(responseStatus).body(response);
	}

	@PostMapping
	public ResponseEntity<QualificationEntity> createQualification(@RequestBody QualificationEntity qualification) {
		QualificationEntity createdQualitification = quantificationService.create(qualification);
		HttpStatus responseStatus = createdQualitification != null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

		return ResponseEntity.status(responseStatus)
				.body(createdQualitification);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getQualificationById(@PathVariable Long id) {
		QualificationEntity qualification = quantificationService.getById(id);

		if (qualification != null) {
			return ResponseEntity.ok().body(qualification);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se encontró la calificacion con ID %d", id));
			msg.put("status", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateQualification(@PathVariable Long id, @RequestBody QualificationEntity qualification) {
		QualificationEntity updatedQualification = quantificationService.updateById(id, qualification);

		if (updatedQualification != null) {
			return ResponseEntity.ok().body(updatedQualification);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo actualizar la calificacion con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteQualification(@PathVariable Long id) {
		boolean deleted = quantificationService.deleteById(id);

		if (deleted) {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.TRUE);
			msg.put("msg", String.format("Calificacion con ID %d eliminado exitosamente", id));
			msg.put("status", HttpStatus.OK.value());
			return ResponseEntity.ok().body(msg);
		} else {
			Map<String, Object> msg = new HashMap<>();
			msg.put("success", Boolean.FALSE);
			msg.put("msg", String.format("No se pudo eliminar el calificacion con ID %d", id));
			msg.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
}
