package com.example.forge.app.infraestructure.webApi.controllers;

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

import com.example.forge.app.application.services.QualificationService;
import com.example.forge.app.domain.entities.QualificationEntity;

@RestController
@RequestMapping("qualification")
public class QualificationController {
	@Autowired
	private QualificationService quantificationService;

	@GetMapping
	public List<QualificationEntity> getAllQualification() {
		return quantificationService.getAll();
	}

	@PostMapping
	public QualificationEntity createQualification(@RequestBody QualificationEntity qualification) {
		return quantificationService.create(qualification);
	}

	@GetMapping("{id}")
	public QualificationEntity getQualification(@PathVariable Long id) {
		return quantificationService.getById(id);
	}

	@PutMapping("{id}")
	public QualificationEntity updateQualification(@PathVariable Long id,
			@RequestBody QualificationEntity qualification) {
		return quantificationService.updateById(id, qualification);
	}

	@DeleteMapping("{id}")
	public boolean deleteQualification(@PathVariable Long id) {
		return quantificationService.deleteById(id);
	}
}
