package com.example.forge.repositories;

import org.springframework.stereotype.Component;

import com.example.forge.baserepository.BaseRepository;
import com.example.forge.models.entities.Student;

@Component
public interface StudentRepository extends BaseRepository<Student>{}
