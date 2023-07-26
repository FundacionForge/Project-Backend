package com.example.forge.repositories;

import org.springframework.stereotype.Component;

import com.example.forge.models.entities.Student;
import com.example.forge.repositorybase.RepositoryBase;

@Component
public interface StudentRepository extends RepositoryBase<Student>{
}
