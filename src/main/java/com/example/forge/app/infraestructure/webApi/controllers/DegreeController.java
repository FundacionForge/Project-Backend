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

import com.example.forge.app.application.services.DegreeService;
import com.example.forge.app.domain.entities.DegreeEntity;

@RestController
@RequestMapping("degree")
public class DegreeController {
	@Autowired
	private DegreeService degreeService;

	@GetMapping
	public List<DegreeEntity> getAllDegree() {
		return degreeService.getAll();
	}

	@PostMapping
	public DegreeEntity createDegree(@RequestBody DegreeEntity degree) {
		return degreeService.create(degree);
	}

	@GetMapping("{id}")
	public DegreeEntity getDegree(@PathVariable Long id) {
		return degreeService.getById(id);
	}

	@PutMapping("{id}")
	public DegreeEntity updateDegree(@PathVariable Long id, @RequestBody DegreeEntity degree) {
		return degreeService.updateById(id, degree);
	}

	@DeleteMapping("{id}")
	public boolean deleteDegree(@PathVariable Long id) {
		return degreeService.deleteById(id);
	}
}
