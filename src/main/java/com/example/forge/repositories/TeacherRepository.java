package com.example.forge.repositories;

import java.util.Optional;

import com.example.forge.models.entities.Teacher;
import com.example.forge.repositorybase.RepositoryBase;

public interface TeacherRepository extends RepositoryBase<Teacher>{
	Optional<Teacher> findByName(String name); 
}
