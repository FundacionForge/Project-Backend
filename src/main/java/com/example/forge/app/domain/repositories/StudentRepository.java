package com.example.forge.app.domain.repositories;

import com.example.forge.app.domain.entities.StudentEntity;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends BaseRepository<StudentEntity> {
	long countByEmail(String email);
	long countByDni(String dni);

}
