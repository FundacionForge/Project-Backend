package com.example.forge.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.forge.models.entities.Student;


public interface StudentRepository extends CrudRepository<Student, Long>, PagingAndSortingRepository<Student, Long> {}