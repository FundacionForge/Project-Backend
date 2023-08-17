package com.example.forge.app.application.services;

import java.util.Optional;

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

	public boolean deleteById(Long id) {
		Optional<StudentEntity> optionalStudent = repository.findById(id);
		if (optionalStudent.isPresent()) {
			repository.delete(optionalStudent.get());
			;
			return true;
		}
		return false;
	}

	private static final int PAGE_SIZE = 10;
	public Page<StudentEntity> studentPage(int pageNumber) {
		PageRequest pageRequest = PageRequest.of(pageNumber - 1, PAGE_SIZE);
		return repository.findAll(pageRequest);
	}
}
