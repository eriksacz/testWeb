package com.test.appweb.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.service.IDibujoService;

@Controller
@SessionAttributes("dibujo")
public class DibujoController {

	@Autowired
	private IDibujoService dibujoService;
	
	 @GetMapping("/listdibu")
		public String listardibu(Model model) {
	    	model.addAttribute("titulo", "Lista de Dibujos");
	    	model.addAttribute("dibujos", dibujoService.findAll());
	    	
			return "dibujos/listdibu";
	}
	 
	 @GetMapping("/formdibu")
		public String creardibu(Model model) {
	    
		 Dibujo dibujo = new Dibujo();
		 
		 model.addAttribute("dibujo", dibujo);
		 model.addAttribute("titulo", "Formulario de Dibujos");
			return "dibujos/formdibu";
	} 
	
	 @PostMapping("/formdibu")
		public String guardadibu(@Valid Dibujo dibujo, BindingResult result,
				Model model,RedirectAttributes flash, SessionStatus statu) {
		 
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Dibujos");
				return "dibujos/formdibu";
			}
			
		// Para saber si fue Creado o Editado
		String mensajeFlash = (dibujo.getId() != null) ? "Dibujo Editada con Exito" : "Dibujo Creada con Exito";

		 
		 dibujoService.save(dibujo);
		// Aqui se elimina la sesion despues de guardar
		 statu.setComplete();
		 flash.addFlashAttribute("success", mensajeFlash);
		 return "redirect:listdibu";
	 }
	 
	 
	 @GetMapping("/formdibu/{id}")
		public String editadibu(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		 Dibujo dibujo = null;
		 
			if(id>0) {
				dibujo = dibujoService.findOne(id);
				if (dibujo == null) {
					flash.addFlashAttribute("error", "El id del Dibujo no Existe en la BD");
					return "redirect:/listcompo";
				}

			} else {
				flash.addFlashAttribute("error", "El id del Dibujo no puede ser Cero");
			
				return "redirect:/listdibu";
			}
			
			 model.addAttribute("dibujo", dibujo);
			 model.addAttribute("titulo", "Formulario de Dibujos");
			
			 return "dibujos/formdibu";
	 }
	 
	// Para ELIMINAR
		@RequestMapping(value = "/eliminardibu/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero
				dibujoService.fetchByIdDibujo(id);
				flash.addFlashAttribute("success", "Dibujo Eliminado con Exito");
			}

			return "redirect:/listdibu";
		}
	 
}

























