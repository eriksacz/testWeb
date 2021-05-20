package com.test.appweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SubmenuController {

	
	 @GetMapping("/submenu")
		public String mostrarIndex(Model model) {
	    	model.addAttribute("titulo", "Submenú");
			return "submenu";
		}


	
}
