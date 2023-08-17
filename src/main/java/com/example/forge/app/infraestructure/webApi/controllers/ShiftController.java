package com.example.forge.app.infraestructure.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.app.application.services.ShiftService;
import com.example.forge.app.domain.entities.ShiftEntity;

@RestController
@RequestMapping("shift")
public class ShiftController {
  @Autowired
  private ShiftService shiftService;

  @GetMapping
  public List<ShiftEntity> getAllStudent(){
      return shiftService.getAll();
  }

  @PostMapping
  public ShiftEntity createCourse(@RequestBody ShiftEntity shift){
      return shiftService.create(shift);
  }

  @GetMapping("{id}")
  public ShiftEntity getCourse(@PathVariable Long id){
      return shiftService.getById(id);
  }

  @PutMapping("{id}")
  public ShiftEntity updateShift(@PathVariable Long id, @RequestBody ShiftEntity shift){
      return shiftService.updateById(id, shift);
  }

  @DeleteMapping("{id}")
  public boolean deleteShift(@PathVariable Long id){
      return shiftService.deleteById(id);
  }
}
