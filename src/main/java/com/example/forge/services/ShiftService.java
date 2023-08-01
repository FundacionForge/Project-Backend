package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Shift;
import com.example.forge.repositories.ShiftRepository;

@Service
public class ShiftService {
  @Autowired
  private ShiftRepository repository;

  public Shift create(Shift shift) {
    return repository.save(shift);
  }

  public List<Shift> getAll(){
    return (List<Shift>) repository.findAll();
  }

  public Optional<Shift> getById(Long id){
    return repository.findById(id);
  }

  public Shift updateById(Long id, Shift updatedShift){
    Optional<Shift> optionalShift = repository.findById(id);
    if(optionalShift.isPresent()){
      Shift shift = optionalShift.get();
      shift.setName(updatedShift.getName());
      return repository.save(shift);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<Shift> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
