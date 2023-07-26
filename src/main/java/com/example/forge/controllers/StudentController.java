package com.example.forge.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.models.entities.Student;
import com.example.forge.services.StudentsService;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public List<Student> getStudent() {
        return studentsService.allStudents();
    }

    @PostMapping
    public Student createdStudent(@RequestBody Student student) {
        return studentsService.createdStudent(student.getDni(), student.getName(), student.getLastName(), student.getMotherLastName(),
                student.getEmail(), student.getPhoneNumber(), student.getAddress());
    }

    @GetMapping("/{id}")
    public Optional<Student> Studentbyid(@PathVariable Long id) {
        return studentsService.getStudentid(id);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentsService.updateStudent(id, student.getDni(), student.getName(), student.getLastName(), student.getMotherLastName(),
                student.getEmail(), student.getPhoneNumber(), student.getAddress());
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable Long id) {
        return studentsService.deleteStudent(id);
    }
}

