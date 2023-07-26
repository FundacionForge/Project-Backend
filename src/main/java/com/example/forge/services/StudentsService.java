package com.example.forge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.forge.models.entities.Student;
import com.example.forge.repositories.StudentRepository;

@Service
public class StudentsService {
    
    @Autowired
    private StudentRepository studentRepository; 

    
    // created Student
    public Student createdStudent(String dni,String name,String lastName, String motherLastName,String email, String phoneNumber, String address) {
        Student student = new Student(dni, name, lastName, motherLastName, email, phoneNumber, address);
        return studentRepository.save(student);
    }

    // all Students
    public List<Student> allStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    // obtain Student by id
    public Optional<Student> getStudentid(Long id) {
        return studentRepository.findById(id);
    }

    // update Student
    public Student updateStudent(Long id,String name,String lastName, String motherLastName,String email, String phoneNumber, String address, String string) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(name);
            student.setLastName(lastName);
            student.setMotherLastName(motherLastName);
            student.setEmail(email);
            student.setPhoneNumber(phoneNumber);
            student.setAddress(address);
            return studentRepository.save(student);
        }
        return null;
    }

    // delete Student by id
    public boolean deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.delete(optionalStudent.get());;
            return true;
        }
        return false;
    } 
}