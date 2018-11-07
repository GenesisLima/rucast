package org.ntvru.rucast.service;

import java.util.List;
import java.util.Optional;

import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService implements RUCastService<Show>{

	@Autowired	
	ShowRepository showRepository;

	@Override
	public void save(Show show) {
		showRepository.save(show);
		
	}

	@Override
	public List<Show> findAll() {
		return (List<Show>) showRepository.findAll();
	}

	@Override
	public Optional<Show> findById(Long id) {
		return showRepository.findById(id);
	}
	
	
public String[] findAllShowNames() {
		
	return showRepository.findAllShowNames();
	}
	


public List<Show> findShowsByName(String showName) {
    return showRepository.findShowsByName(showName);
}
	
public Optional<Show> findShowByName(String showName) {
    return showRepository.findShowByName(showName);
}

}
