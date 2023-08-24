package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import com.example.forge.app.domain.entities.TeacherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.StudentEntity;
import com.example.forge.app.domain.repositories.StudentRepository;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseService;

@Service
public class StudentService extends BaseService<StudentEntity> {
	@Autowired
	private StudentRepository repository;

	public StudentEntity updateById(Long id, StudentEntity updatedStudent) {
		Optional<StudentEntity> optionalStudent = repository.findById(id);
		if (optionalStudent.isPresent()) {
			StudentEntity student = optionalStudent.get();
			student.setName(updatedStudent.getName());
			student.setLastName(updatedStudent.getLastName());
			student.setMotherLastName(updatedStudent.getMotherLastName());
			student.setEmail(updatedStudent.getEmail());
			student.setPhoneNumber(updatedStudent.getPhoneNumber());
			student.setAddress(updatedStudent.getAddress());
			return repository.save(student);
		}
		return null;
	}

	public boolean isEmailDuplicated(String email) {
		long count = repository.countByEmail(email);
		return count > 0;
	}

	public boolean isDniDuplicated(String dni) {
		long count = repository.countByDni(dni);
		return count > 0;
	}

	public Page<StudentEntity> studentPerPage(Integer pageNumber, Integer size) {
		if (pageNumber == null) {
			pageNumber = 0;
			size = ((List<StudentEntity>) repository.findAll()).size();
		} else if (size == null) {
			size = 10;
		}
		PageRequest pageRequest = PageRequest.of(pageNumber, size);
		return repository.findAll(pageRequest);
	}
}
