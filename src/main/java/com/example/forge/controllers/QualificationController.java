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

import com.example.forge.models.entities.Qualification;
import com.example.forge.services.QualificationService;

@RestController
@RequestMapping("qualification")
public class QualificationController {
  @Autowired
  private QualificationService service;

  @GetMapping
  public List<Qualification> getAllQualification(){
      return service.getAll();
  }

  @PostMapping
  public Qualification createQualification(@RequestBody Qualification qualification){
      return service.create(qualification);
  }

  @GetMapping("{id}")
  public Optional<Qualification> getQualification(@PathVariable Long id){
      return service.getById(id);
  }

  @PutMapping("{id}")
  public Qualification updateQualification(@PathVariable Long id, @RequestBody Qualification qualification){
      return service.updateById(id, qualification);
  }

  @DeleteMapping("{id}")
  public boolean deleteQualification(@PathVariable Long id){
      return service.deleteById(id);
  }
}
