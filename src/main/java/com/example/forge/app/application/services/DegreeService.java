package com.example.forge.app.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.DegreeEntity;
import com.example.forge.app.domain.repositories.DegreeRepository;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseService;

@Service
public class DegreeService extends BaseService<DegreeEntity> {
  @Autowired
  private DegreeRepository repository;

  public DegreeEntity updateById(Long id, DegreeEntity updatedDegree){
    Optional<DegreeEntity> optionalDegree = repository.findById(id);
    if(optionalDegree.isPresent()){
      DegreeEntity degree = optionalDegree.get();
      degree.setName(updatedDegree.getName());
      degree.setAcademicLevel(updatedDegree.getAcademicLevel());
      degree.setAssignedRoom(updatedDegree.getAssignedRoom());
      return repository.save(degree);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<DegreeEntity> optionalCourse = repository.findById(id);
    if(optionalCourse.isPresent()){
      repository.delete(optionalCourse.get());
      return true;
    }
    return false;
  }
}
