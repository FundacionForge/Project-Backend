package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Degree;
import com.example.forge.repositories.DegreeRepository;

@Service
public class DegreeService {
  @Autowired
  private DegreeRepository repository;

  public Degree create(Degree degree) {
    return repository.save(degree);
  }

  public List<Degree> getAll(){
    return (List<Degree>) repository.findAll();
  }

  public Optional<Degree> getById(Long id){
    return repository.findById(id);
  }

  public Degree updateById(Long id, Degree updatedDegree){
    Optional<Degree> optionalDegree = repository.findById(id);
    if(optionalDegree.isPresent()){
      Degree degree = optionalDegree.get();
      degree.setName(updatedDegree.getName());
      degree.setAcademicLevel(updatedDegree.getAcademicLevel());
      degree.setAssignedRoom(updatedDegree.getAssignedRoom());
      return repository.save(degree);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<Degree> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
