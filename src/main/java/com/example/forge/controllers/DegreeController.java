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

import com.example.forge.models.entities.Degree;
import com.example.forge.services.DegreeService;

@RestController
@RequestMapping("/api/degree")
public class DegreeController {
  @Autowired
  private DegreeService service;

  @GetMapping
  public List<Degree> getAllDegree(){
      return service.getAll();
  }

  @PostMapping
  public Degree createDegree(@RequestBody Degree degree){
      return service.create(degree);
  }

  @GetMapping("{id}")
  public Optional<Degree> getDegree(@PathVariable Long id){
      return service.getById(id);
  }

  @PutMapping("{id}")
  public Degree updateDegree(@PathVariable Long id, @RequestBody Degree degree){
      return service.updateById(id, degree);
  }

  @DeleteMapping("{id}")
  public boolean deleteDegree(@PathVariable Long id){
      return service.deleteById(id);
  }
}
