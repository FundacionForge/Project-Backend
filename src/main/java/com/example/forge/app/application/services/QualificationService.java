package com.example.forge.app.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.QualificationEntity;
import com.example.forge.app.domain.repositories.QualificationRepository;

@Service
public class QualificationService {
  @Autowired
  private QualificationRepository repository;

  public QualificationEntity create(QualificationEntity qualification) {
    return repository.save(qualification);
  }

  public List<QualificationEntity> getAll(){
    return (List<QualificationEntity>) repository.findAll();
  }

  public Optional<QualificationEntity> getById(Long id){
    return repository.findById(id);
  }

  public QualificationEntity updateById(Long id, QualificationEntity updatedQualification){
    Optional<QualificationEntity> optionalQualification = repository.findById(id);
    if(optionalQualification.isPresent()){
      QualificationEntity qualification = optionalQualification.get();
      qualification.setName(updatedQualification.getName());
      return repository.save(qualification);
    }
    return null;
  }

  public boolean deleteById(Long id) {
    Optional<QualificationEntity> optionalQualification = repository.findById(id);
    if(optionalQualification.isPresent()){
      repository.delete(optionalQualification.get());
      return true;
    }
    return false;
  }
}
