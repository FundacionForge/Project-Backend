package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import com.example.forge.app.domain.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.TeacherEntity;
import com.example.forge.app.domain.repositories.TeacherRepository;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseService;

@Service
public class TeacherService extends BaseService<TeacherEntity> {
	@Autowired
	private TeacherRepository repository;

	public TeacherEntity updateById(Long id, TeacherEntity updatedTeacher) {
		Optional<TeacherEntity> optionalTeacher = repository.findById(id);
		if (optionalTeacher.isPresent()) {
			TeacherEntity teacher = optionalTeacher.get();
			teacher.setDni(updatedTeacher.getDni());
			teacher.setName(updatedTeacher.getName());
			teacher.setLastName(updatedTeacher.getLastName());
			teacher.setEmail(updatedTeacher.getEmail());
			teacher.setAddress(updatedTeacher.getAddress());
			teacher.setQualification(updatedTeacher.getQualification());
			teacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
			return repository.save(teacher);
		}
		return null;
	}

	public boolean deleteById(Long id) {
		Optional<TeacherEntity> optionalStudent = repository.findById(id);
		if (optionalStudent.isPresent()) {
			repository.delete(optionalStudent.get());
			;
			return true;
		}
		return false;
	}

	public Page<TeacherEntity> teacherPerPage(Integer pageNumber, Integer size) {
		if (pageNumber == null) {
			pageNumber = 0;
			size = ((List<TeacherEntity>) repository.findAll()).size();
		} else if (size == null) {
			size = 10;
		}
		PageRequest pageRequest = PageRequest.of(pageNumber, size);
		return repository.findAll(pageRequest);
	}
}
