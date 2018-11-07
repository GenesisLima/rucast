package org.ntvru.rucast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(){
	//	System.out.println("PASSANDO PELO HOME CONTROLLER!");
		return "main";
	}
	
	
	@RequestMapping("/index")
	public String index() {
		
		return "new_index";
	}
	
 
//	@RequestMapping(value="/login", method=RequestMethod.GET)	
//	public String login() {		
//		System.out.println("LOGIN PAGE");
//		return "login";
//	}
	
}
