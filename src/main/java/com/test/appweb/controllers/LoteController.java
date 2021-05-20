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

import com.test.appweb.models.entity.Lote;
import com.test.appweb.models.service.ILoteService;

@Controller
@SessionAttributes("lote")
public class LoteController {
	
	@Autowired
	private ILoteService loteService;
	
	 @GetMapping("/listlot")
		public String listarlot(Model model) {
	    	model.addAttribute("titulo", "Lista de Lotes");
	    	model.addAttribute("lotes", loteService.findAll());
	    	return "lotes/listlot";
	}
	 
	 @GetMapping("/formlot")
		public String crearlot(Model model) {
		 
		 	Lote lote = new Lote();
		 
		 	model.addAttribute("lote", lote);
	    	model.addAttribute("titulo", "Formulario de Lotes");
	    	
			return "lotes/formlot";
	} 
	 
	 @PostMapping("/formlot")
		public String guardarlot(@Valid Lote lote, BindingResult result
				,Model model,RedirectAttributes flash, SessionStatus statu) {
		 
		 if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Lotes");
				return "lotes/formlot";
			}
		 
		// Para saber si fue Creado o Editado
		   String mensajeFlash = (lote.getId() != null) ? "Lote Editado con Exito" : "Lote Creado con Exito";

		 
		 loteService.save(lote);
		// Aqui se elimina la sesion despues de guardar
			statu.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
		 return "redirect:listlot";
	 }
	 
	 @GetMapping("/formlot/{id}")
		public String editarcompo(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {

		 Lote lote = null;
		 
		 if(id>0) {
			 lote = loteService.findOne(id);
				if (lote == null) {
					flash.addFlashAttribute("error", "El id del lote no Existe en la BD");
					return "redirect:/listlot";
				}

			} else {
				flash.addFlashAttribute("error", "El id del lote no puede ser Cero");
			
				return "redirect:/listot";
			}
			
			 model.addAttribute("lote", lote);
			 model.addAttribute("titulo", "Formulario de Lotes");
			
		 
		 return "lotes/formlot";
	 } 
	 
	// Para ELIMINAR
		@RequestMapping(value = "/eliminarlot/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero
				loteService.fetchByIdLote(id);
				flash.addFlashAttribute("success", "Lote Eliminado con Exito");
			}

			return "redirect:/listlot";
		}
		

}

























