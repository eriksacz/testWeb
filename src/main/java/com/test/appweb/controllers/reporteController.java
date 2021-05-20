package com.test.appweb.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.test.appweb.models.entity.reporte;
import com.test.appweb.models.service.IreporteService;

@Controller
public class reporteController {

	@Autowired
	private IreporteService reporteService;
	
	@RequestMapping(value = "/reportes", method = RequestMethod.GET)
	public String listar(Model model) {

	     List<reporte> reportess = reporteService.findAll();

		model.addAttribute("titulo", "sin titulo");
		model.addAttribute("reportess", reportess);
		
		return "reportes";
	}
	
}
