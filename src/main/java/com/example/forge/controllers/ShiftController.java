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

import com.example.forge.models.entities.Shift;
import com.example.forge.services.ShiftService;

@RestController
@RequestMapping("shift")
public class ShiftController {
  @Autowired
  private ShiftService service;

  @GetMapping
  public List<Shift> getAllStudent(){
      return service.getAll();
  }

  @PostMapping
  public Shift createCourse(@RequestBody Shift shift){
      return service.create(shift);
  }

  @GetMapping("{id}")
  public Optional<Shift> getCourse(@PathVariable Long id){
      return service.getById(id);
  }

  @PutMapping("{id}")
  public Shift updateShift(@PathVariable Long id, @RequestBody Shift shift){
      return service.updateById(id, shift);
  }

  @DeleteMapping("{id}")
  public boolean deleteShift(@PathVariable Long id){
      return service.deleteById(id);
  }
}
