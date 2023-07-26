package com.example.forge.repositories;

import java.util.Optional;
import com.example.forge.models.entities.Student;
import com.example.forge.repositorybase.RepositoryBase;

public interface StudentRepository extends RepositoryBase<Student>{
	Optional<Student> findByName(String name); 
}
