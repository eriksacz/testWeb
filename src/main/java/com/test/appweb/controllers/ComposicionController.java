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

import com.test.appweb.models.entity.Composicion;
import com.test.appweb.models.service.IComposicionService;


@Controller
@SessionAttributes("composicion")
public class ComposicionController {

	@Autowired
	private IComposicionService composicionService;
	
	 @GetMapping("/listcompo")
		public String listarcompo(Model model) {
	    	model.addAttribute("titulo", "Lista de Composiciones");
	    	model.addAttribute("composiciones", composicionService.findAll());
			return "composiciones/listcompo";
	}
	 
	 @GetMapping("/formcompo")
		public String crearcompo(Model model) {
	 
		 Composicion composicion = new Composicion();
		 
		 model.addAttribute("composicion", composicion);
		 model.addAttribute("titulo", "Formulario de Composiciones");
		
		 return "composiciones/formcompo";
	}
	 
	@PostMapping("/formcompo")
	public String guardarcompo(@Valid Composicion composicion, BindingResult result
			,Model model,RedirectAttributes flash, SessionStatus statu) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Composiciones");
			return "composiciones/formcompo";
		}
		
		// Para saber si fue Creado o Editado
	    String mensajeFlash = (composicion.getId() != null) ? "Composición Editada con Exito" : "Composición Creada con Exito";

		
		composicionService.save(composicion);
		// Aqui se elimina la sesion despues de guardar
		statu.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listcompo";
	}
	 
	
	@GetMapping("/formcompo/{id}")
	public String editarcompo(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		Composicion composicion = null;
		
		if(id>0) {
			composicion = composicionService.findOne(id);
			if (composicion == null) {
				flash.addFlashAttribute("error", "El id de la Composición no Existe en la BD");
				return "redirect:/listcompo";
			}

		} else {
			flash.addFlashAttribute("error", "El id de la Composición no puede ser Cero");
		
			return "redirect:/listcompo";
		}
		
		 model.addAttribute("composicion", composicion);
		 model.addAttribute("titulo", "Formulario de Composiciones");
		
		return "composiciones/formcompo";
	}
	
	// Para ELIMINAR
	@RequestMapping(value = "/eliminarcompo/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {

			//solo se pone en cero
			composicionService.fetchByIdComposicion(id);
			flash.addFlashAttribute("success", "Composición Eliminada con Exito");
		}

		return "redirect:/listcompo";
	}
	
}




