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

import com.test.appweb.models.entity.Proveedor;
import com.test.appweb.models.service.IProveedorService;

@Controller
@SessionAttributes("proveedor")
public class ProveedorControler {

	
	@Autowired
	private IProveedorService proveedorService;
	
	 @GetMapping("/listprov")
		public String listarprov(Model model) {
	    	model.addAttribute("titulo", "Lista de Proveedores");
	    	model.addAttribute("proveedores", proveedorService.findAll());
			return "proveedores/listprov";
	}
	 
	 @GetMapping("/formprov")
		public String crearprov(Model model) {
	    	
		 Proveedor proveedor = new Proveedor();
		 
		  model.addAttribute("proveedor", proveedor);
		  model.addAttribute("titulo", "Formulario de Proveedores");
			
		  return "proveedores/formprov";
	} 
	 
		@PostMapping("/formprov")
		public String guardarprov(@Valid Proveedor proveedor, BindingResult result
				,Model model,RedirectAttributes flash, SessionStatus statu) {
			
			if(result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Proveedores");
				return "proveedores/formprov";
			}
			
			// Para saber si fue Creado o Editado
		    String mensajeFlash = (proveedor.getId() != null) ? "Editado con Exito" : "Creado con Exito";

			proveedorService.save(proveedor);
			// Aqui se elimina la sesion despues de guardar
			statu.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:listprov";
		}
	
		@GetMapping("/formprov/{id}")
		public String editarcompo(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		
			Proveedor proveedor = null;
			
			if(id>0) {
				proveedor = proveedorService.findOne(id);
				if (proveedor == null) {
					flash.addFlashAttribute("error", "El id del Proveedor no Existe en la BD");
					return "redirect:/listprov";
				}

			} else {
				flash.addFlashAttribute("error", "El id del Proveedor no puede ser Cero");
			
				return "redirect:/listprov";
			}
			
			 model.addAttribute("proveedor", proveedor);
			 model.addAttribute("titulo", "Formulario de Proveedores");
			
			return "proveedores/formprov";
		}
		
		
		// Para ELIMINAR
		@RequestMapping(value = "/eliminarprov/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero
				proveedorService.fetchByIdProveedor(id);
				flash.addFlashAttribute("success", "Proveedor Eliminado con Exito");
			}

			return "redirect:/listprov";
		}
		
}





















