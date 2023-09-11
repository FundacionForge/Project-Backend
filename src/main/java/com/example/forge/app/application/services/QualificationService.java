package com.example.forge.app.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.forge.app.domain.entities.QualificationEntity;
import com.example.forge.app.domain.repositories.QualificationRepository;
import com.example.forge.app.infraestructure.shared.abstractBase.BaseService;

@Service
public class QualificationService extends BaseService<QualificationEntity> {
	@Autowired
	private QualificationRepository repository;

	public QualificationEntity updateById(Long id, QualificationEntity updatedQualification) {
		Optional<QualificationEntity> optionalQualification = repository.findById(id);
		if (optionalQualification.isPresent()) {
			QualificationEntity qualification = optionalQualification.get();
			qualification.setName(updatedQualification.getName());
			return repository.save(qualification);
		}
		return null;
	}

	public boolean deleteById(Long id) {
		Optional<QualificationEntity> optionalQualification = repository.findById(id);
		if (optionalQualification.isPresent()) {
			repository.delete(optionalQualification.get());
			return true;
		}
		return false;
	}
}
