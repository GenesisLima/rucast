package org.ntvru.rucast.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ntvru.rucast.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {

	

	 Optional<Category> findCategoryByName(@Param("categoryName") String categoryName);
	 
	 
	 @Query("select c.id, c.name, c.description from Category c")
	 List<Category>  findAllCategoryNames();

	 
}