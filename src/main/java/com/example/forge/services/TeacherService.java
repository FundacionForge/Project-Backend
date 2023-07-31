package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Teacher;
import com.example.forge.repositories.TeacherRepository;

@Service
public class TeacherService {
	@Autowired
	private TeacherRepository repository;

	public List<Teacher> getAll(){
		return (List<Teacher>) repository.findAll();
	}

	public Teacher create(Teacher teacher) {
		return (Teacher) repository.save(teacher);
	}

	public Teacher getById(Long id) {
		Optional <Teacher> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Teacher updateById(Long id, Teacher teacherData) {
		Teacher teacher = repository.findById(id).orElse(null);
		if(teacher == null) {
			throw new IllegalArgumentException("El libro con el ID proporcionado no existe");
		}
		teacher.setDni(teacherData.getDni());
		teacher.setName(teacherData.getName());
		teacher.setLastName(teacherData.getLastName());
		teacher.setEmail(teacherData.getEmail());
		teacher.setAddress(teacherData.getAddress());
		teacher.setQualification(teacher.getQualification());
		teacher.setPhoneNumber(teacherData.getPhoneNumber());
		return create(teacher);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
