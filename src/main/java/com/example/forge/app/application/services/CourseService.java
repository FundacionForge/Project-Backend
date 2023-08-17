package com.example.forge.app.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.CourseEntity;
import com.example.forge.app.domain.repositories.CourseRepository;
import com.example.forge.app.infraestructure.shared.BaseService;

@Service
public class CourseService extends BaseService<CourseEntity> {
	@Autowired
	private CourseRepository repository;

	public CourseEntity updateById(Long id, CourseEntity updatedCourse) {
		Optional<CourseEntity> optionalCourse = repository.findById(id);
		if (optionalCourse.isPresent()) {
			CourseEntity course = optionalCourse.get();
			course.setName(updatedCourse.getName());
			course.setDescription(updatedCourse.getDescription());
			course.setImage(updatedCourse.getImage());
			return repository.save(course);
		}
		return null;
	}

	public boolean deleteById(Long id) {
		Optional<CourseEntity> optionalCourse = repository.findById(id);
		if (optionalCourse.isPresent()) {
			repository.delete(optionalCourse.get());
			return true;
		}
		return false;
	}
}
