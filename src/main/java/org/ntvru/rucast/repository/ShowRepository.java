package org.ntvru.rucast.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.ntvru.rucast.model.Show;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface ShowRepository extends CrudRepository<Show, Long>{

	 String[] findAllShowNames();
	
     List<Show> findShowsByName(@Param("showName") String showName);
	 
	 Optional<Show> findShowByName(@Param("showName") String showName); 
	 
	 
	
}
