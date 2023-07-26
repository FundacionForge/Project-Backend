package com.example.forge.repositorybase;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryBase<T> extends CrudRepository<T, Long> {
	List<T> findAll();
	void deleteById();
}
