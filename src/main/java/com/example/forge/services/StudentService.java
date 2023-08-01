package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Student;
import com.example.forge.repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student create(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return (List<Student>) repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }


    public Student updateById(Long id, Student updatedStudent) {
      Optional<Student> optionalStudent = repository.findById(id);
      if (optionalStudent.isPresent()) {
        Student student = optionalStudent.get();
        student.setName(updatedStudent.getName());
        student.setLastName(updatedStudent.getLastName());
        student.setMotherLastName(updatedStudent.getMotherLastName());
        student.setEmail(updatedStudent.getEmail());
        student.setPhoneNumber(updatedStudent.getPhoneNumber());
        student.setAddress(updatedStudent.getAddress());
        return repository.save(student);
      }
       return null;
    }


    public boolean deleteById(Long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            repository.delete(optionalStudent.get());
            ;
            return true;
        }
        return false;
    }
}
