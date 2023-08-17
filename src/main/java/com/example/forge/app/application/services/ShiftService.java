package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.ShiftEntity;
import com.example.forge.app.domain.repositories.ShiftRepository;

@Service
public class ShiftService {
  @Autowired
  private ShiftRepository repository;

  public ShiftEntity create(ShiftEntity shift) {
    return repository.save(shift);
  }

  public List<ShiftEntity> getAll(){
    return (List<ShiftEntity>) repository.findAll();
  }

  public Optional<ShiftEntity> getById(Long id){
    return repository.findById(id);
  }

  public ShiftEntity updateById(Long id, ShiftEntity updatedShift){
    Optional<ShiftEntity> optionalShift = repository.findById(id);
    if(optionalShift.isPresent()){
      ShiftEntity shift = optionalShift.get();
      shift.setName(updatedShift.getName());
      return repository.save(shift);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<ShiftEntity> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
