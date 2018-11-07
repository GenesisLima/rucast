package org.ntvru.rucast.service;

import java.util.List;
import java.util.Optional;

import org.ntvru.rucast.model.Category;
import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryService implements RUCastService<Category>{
	
	@Autowired
	private CategoryRepository categoryRepository;
	

	@Override
	public void save(Category category) {
		categoryRepository.save(category);	
	}

	@Override
	public List<Category> findAll() {
		
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Optional<Category> findById(Long id) {
		
		return categoryRepository.findById(id);
	}

	
	public Optional<Category> findCategoryByName(String categoryName) {
	    return categoryRepository.findCategoryByName(categoryName);
	}
	
	
	public List<Category> findAllCategoryNames(){
		
		return categoryRepository.findAllCategoryNames();
	}
	
	
	
	

	
}
