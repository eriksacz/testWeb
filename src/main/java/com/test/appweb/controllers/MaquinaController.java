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
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.service.IMaquinaService;

@Controller
@SessionAttributes("maquina")
public class MaquinaController {
	
	@Autowired
	private IMaquinaService maquinaService;
	
	 @GetMapping("/listmaquina")
		public String listarmaquina(Model model) {
	    	model.addAttribute("titulo", "Lista de Máquinas");
	    	model.addAttribute("maquinas", maquinaService.findAll());
			return "maquinas/listmaquina";
	}
	 
	 @GetMapping("/formmaquina")
		public String crearmaquina(Model model) {
	    
		 Maquina maquina = new Maquina();
		 
		 model.addAttribute("maquina", maquina);
		 model.addAttribute("titulo", "Formulario de Máquinas");
			return "maquinas/formmaquina";
	} 
	 
	 
	 @PostMapping("/formmaquina")
		public String guardaemaquina(@Valid Maquina maquina, BindingResult result,
				Model model,RedirectAttributes flash, SessionStatus statu) {
		 
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Máquinas");
				return "maquinas/formmaquina";
			}
			
		// Para saber si fue Creado o Editado
	    String mensajeFlash = (maquina.getId() != null) ? "Máquina Editada con Exito" : "Máquina Creada con Exito";

		 
		 maquinaService.save(maquina);
		// Aqui se elimina la sesion despues de guardar
		 statu.setComplete();
		 flash.addFlashAttribute("success", mensajeFlash);
		 return "redirect:listmaquina";
	 }
	 
	 @GetMapping("/formmaquina/{id}")
		public String editamaquina(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		 Maquina maquina = null;
		 
			if(id>0) {
				maquina = maquinaService.findOne(id);
				if (maquina == null) {
					flash.addFlashAttribute("error", "El id de la Máquina no Existe en la BD");
					return "redirect:/listcompo";
				}

			} else {
				flash.addFlashAttribute("error", "El id de la Máquina no puede ser Cero");
			
				 return "redirect:/listmaquina";
			}
			
			 model.addAttribute("maquina", maquina);
			 model.addAttribute("titulo", "Formulario de Máquinas");
			
			 return "maquinas/formmaquina";
	 }
	 
	// Para ELIMINAR
			@RequestMapping(value = "/eliminarmaquina/{id}")
			public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

				if (id > 0) {

					//solo se pone en cero
					maquinaService.fetchByIdMaquina(id);
					flash.addFlashAttribute("success", "Máquina Eliminada con Exito");
				}

				return "redirect:/listmaquina";
			}
			
			@RequestMapping(value = "/fueramaquina/{id}")
			public String fuera(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

				if (id > 0) {

					//solo se pone en cero

					maquinaService.fetchByIdMaquinados(id);
					flash.addFlashAttribute("error", "Fuera de Servicio");
				}

				return "redirect:/listmaquina";
			}
			
			@RequestMapping(value = "/serviciomaquina/{id}")
			public String servicio(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

				if (id > 0) {

					//solo se pone en uno
					maquinaService.fetchByIdMaquinatres(id);
					flash.addFlashAttribute("success", "En Servicio");
				}

				return "redirect:/listmaquina";
			}

}
