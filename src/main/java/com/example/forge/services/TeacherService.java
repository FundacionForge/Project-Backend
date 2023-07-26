package com.example.forge.services;

import org.springframework.stereotype.Service;

import com.example.forge.coreservice.CoreService;
import com.example.forge.models.entities.Teacher;
import com.example.forge.repositories.TeacherRepository;

@Service
public class TeacherService extends CoreService<Teacher> {

	public TeacherService(TeacherRepository repository) {
		super(repository);
	}
}
