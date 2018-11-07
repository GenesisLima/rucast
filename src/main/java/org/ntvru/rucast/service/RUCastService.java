package org.ntvru.rucast.service;

import java.util.List;
import java.util.Optional;

import org.ntvru.rucast.model.Category;
import org.ntvru.rucast.model.Show;

public interface RUCastService<T> {

	 void save(T t);
	 
	 List<T> findAll();
	 
	 Optional<T> findById(Long id);
	 
}
