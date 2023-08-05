package com.example.forge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.models.entities.Qualification;
import com.example.forge.repositories.QualificationRepository;

@Service
public class QualificationService {
  @Autowired
  private QualificationRepository repository;

  public Qualification create(Qualification qualification) {
    return repository.save(qualification);
  }

  public List<Qualification> getAll(){
    return (List<Qualification>) repository.findAll();
  }

  public Optional<Qualification> getById(Long id){
    return repository.findById(id);
  }

  public Qualification updateById(Long id, Qualification updatedQualification){
    Optional<Qualification> optionalQualification = repository.findById(id);
    if(optionalQualification.isPresent()){
      Qualification qualification = optionalQualification.get();
      qualification.setName(updatedQualification.getName());
      return repository.save(qualification);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<Qualification> optionalQualification = repository.findById(id);
    if(optionalQualification.isPresent()){
      repository.delete(optionalQualification.get());
      return true;
    }
    return false;
  }
}
