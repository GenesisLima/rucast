package org.ntvru.rucast.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Genesis Lima
 */


@Service
public class EpisodeService {

	
	
	@Autowired
	private EpisodeRepository episodeRepository;
	
	
	
	
	
	
	
	
	public void save(Episode show) throws UnknownHostException{		 
		episodeRepository.save(show);
		 
		
	}
	
	//public void save(String showName, String showTopic,String showSynopsis){}
	
	public List<Episode> findAll() throws UnknownHostException {
            List<Episode> episodes = new ArrayList<>();
            episodeRepository.findAll().forEach(episodes::add);      
        return episodes;

    }
//	
//	  
//        
//	    public Show find(Show show) throws UnknownHostException {
//
//	       // return repository.findOne(id);
//	    	return pureShowRepository.readShow(show);
//	    }
//	
//	    
	    public Optional<Episode> findById(Long id) throws UnknownHostException {

		       // return repository.findOne(id);
		    	return episodeRepository.findById(id);
		    }
	    
	
}
