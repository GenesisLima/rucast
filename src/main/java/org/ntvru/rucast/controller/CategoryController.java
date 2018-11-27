package org.ntvru.rucast.controller;

import java.util.List;

import javax.ws.rs.Produces;

import org.ntvru.rucast.model.Category;
import org.ntvru.rucast.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
//	@RequestMapping(method=RequestMethod.POST)
//	public ModelAndView addCategory(ModelAndView modelAndView, Category category, RedirectAttributes redirectAttributes) {		  
//
//		service.save(category);
//		redirectAttributes.addFlashAttribute("message", "Categoria salva com sucesso!");		
//		modelAndView.setViewName("redirect:/");
//		
//		return modelAndView;
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String addCategory(ModelAndView modelAndView, @RequestBody Category category, RedirectAttributes redirectAttributes) {		  
         System.out.println("CATEGORY "+category);
		service.save(category);
		redirectAttributes.addFlashAttribute("message", "Categoria salva com sucesso!");		
//		modelAndView.setViewName("redirect:/");
		
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Category> getAllCategories(ModelAndView modelAndView) {		  
          
		List<Category> categories = service.findAll(); 
		
		modelAndView.addObject("categories", categories);
		//modelAndView.setViewName("/list");
		
		return categories;
	}
	
	@RequestMapping(value="/api/categories", method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody List<Category> listCategories(){	
		System.out.println("REQUEST "+this.toString()+" AJAX ");
		System.out.println(service.findAll());
		return service.findAll();
	}
	
	@RequestMapping(value="/api/categoriesNames", method=RequestMethod.GET)
	public @ResponseBody String[] listCategoriesArray(){		
		return new String[] {"TEST1", "TEST2"};
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@Produces("application/json")
	public @ResponseBody Category getCategory(ModelAndView modelAndView, @PathVariable Long id){	
		//modelAndView.addObject("category", service.findById(id).get());
		//modelAndView.setViewName("/public/category.html");
		System.out.println("ID "+service.findById(id).get());
		return service.findById(id).get();
	}
}
