package com.example.forge.coreservice;

import java.util.List;
import java.util.Optional;

import com.example.forge.repositorybase.RepositoryBase;

public class CoreService<T> {
	private RepositoryBase<T> repositoryBase;
	
	public CoreService(RepositoryBase<T> repositoryBase) {
		this.repositoryBase = repositoryBase;
	}
	
	public List<T> findAll(){
		return repositoryBase.findAll();
	}
	
	public T save(T object) {
		return repositoryBase.save(object);
	}
	
	public T findById(Long id) {
		Optional <T> optional = repositoryBase.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public T update(T object) {
		return save(object);
	}
	
	public void deleteById(Long id) {
		repositoryBase.deleteById(id);
	}
}
