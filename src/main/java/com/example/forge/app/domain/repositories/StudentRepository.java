package com.example.forge.app.domain.repositories;

import com.example.forge.app.domain.entities.StudentEntity;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseRepository;

public interface StudentRepository extends BaseRepository<StudentEntity> {
	long countByEmail(String email);
	long countByDni(String dni);
}
