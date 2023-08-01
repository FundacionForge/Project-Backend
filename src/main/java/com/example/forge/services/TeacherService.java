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

	public Teacher updateById(Long id, Teacher updatedTeacher) {
    Optional<Teacher> optionalTeacher = repository.findById(id);
		if (optionalTeacher.isPresent()) {
      Teacher teacher = optionalTeacher.get();
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

	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}
