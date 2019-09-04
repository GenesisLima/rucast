package org.ntvru.rucast.controller;

import java.util.List;

import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.model.dto.adapter.ShowDTOAdapter;
import org.ntvru.rucast.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ShowController {

	@Autowired	
	ShowService service;
	
	@Autowired
	ShowDTOAdapter showAdapter;
	
	@RequestMapping(method=RequestMethod.POST)
	public String addProduct(ModelAndView modelAndView, @RequestBody Show show, RedirectAttributes redirectAttributes) {
		service.save(show);
		redirectAttributes.addFlashAttribute("message", "Produto salvo com sucesso!");			
		return "redirect:/";
	}
	

	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<Show> listShows(){	
		System.out.println("REQUEST "+this.toString()+" AJAX ");
		//System.out.println("LIST "+service.findAll());
		return service.findAll();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody Show getShowById(@PathVariable Long id){		
		
		return service.findById(id).get();
	}
	
	
	@RequestMapping(value="/names",method=RequestMethod.GET)
	public @ResponseBody List<Show> getShowNames(){
		return showAdapter.getShowNamesOnly();
	}
	
}
