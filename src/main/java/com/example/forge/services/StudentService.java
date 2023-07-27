package com.example.forge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.forge.models.entities.Student;
import com.example.forge.repositories.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository repository; 

    
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> allStudents() {
        return (List<Student>) repository.findAll();
    }

    public Optional<Student> getStudentbyid(Long id) {
        return repository.findById(id);
    }

    public Student updateStudent(Long id,String name,String lastName, String motherLastName,String email, String phoneNumber, String address, String string) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (!optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(name);
            student.setLastName(lastName);
            student.setMotherLastName(motherLastName);
            student.setEmail(email);
            student.setPhoneNumber(phoneNumber);
            student.setAddress(address);
            return repository.save(student);
        }
        return null;
    }

    public boolean deleteStudent(Long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            repository.delete(optionalStudent.get());;
            return true;
        }
        return false;
    }

    public Student create(Student student) {
        return null;
    } 
}