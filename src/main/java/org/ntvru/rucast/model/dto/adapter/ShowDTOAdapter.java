package org.ntvru.rucast.model.dto.adapter;

import java.util.ArrayList;
import java.util.List;

import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShowDTOAdapter {
	
	@Autowired
	private ShowRepository repository;
	private Show show;
	
	
	public ShowDTOAdapter(Show show) {
		this.show = show;		
	}
	

	public ShowDTOAdapter() {
		super();		
	}
	
	private class ShowDTO{
		private Long id;	
		private String name;
		
		
		public ShowDTO(Long id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	
	public List<Show> getShowNamesOnly() {
		List<Show> showDTOs = new ArrayList<Show>();		
		// repository.findAll().forEach(showDTOs.add(new ShowDTO(show.getId(), show.getName())));
		for(Show show :repository.findAll()) {
		  showDTOs.add(new Show(show.getId(), show.getName()));
		  }
		return  showDTOs;
	}

}
