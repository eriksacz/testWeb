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
import com.test.appweb.models.entity.ContentClient;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IComposicionService;
import com.test.appweb.models.service.IContentClientService;
import com.test.appweb.models.service.IDibujoService;
import com.test.appweb.models.service.ILoteService;
import com.test.appweb.models.service.IProveedorService;

@Controller
@SessionAttributes("contentClient")
public class ContentClientContoller {
	
	
	@Autowired
	private IContentClientService contentClientService;
	
	@Autowired
	private IProveedorService proveedorService;
	
	@Autowired
	private ILoteService loteService;
	
	@Autowired
	private IDibujoService dibujoService;
	
	@Autowired
	private IComposicionService composicionService;
	
	@Autowired
	private IClienteService clienteService;
	
	 @GetMapping("/listcontentClie")
		public String listarcompo(Model model) {
	    	model.addAttribute("titulo", "Lista de Detalles Clientes");
	    	model.addAttribute("contentClient", contentClientService.findAll());
			return "contentClie/listcontentClie";
	}
	 
	 
	@GetMapping("/formcontentClient")
	public String crarcontentClient(Model model) {
	 
		ContentClient contentClient = new ContentClient();

		model.addAttribute("contentClient", contentClient);
		model.addAttribute("proveedores", proveedorService.findAll());
		model.addAttribute("lotes", loteService.findAll());
		model.addAttribute("dibujos", dibujoService.findAll());
		model.addAttribute("composiciones", composicionService.findAll());
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("titulo", "Formulario de detalles Clientes");
			
		
	 return "contentClie/formcontentClient";
	}
	
	@PostMapping("/formcontentClient")
	public String guardarcontentClient(@Valid ContentClient contentClient, BindingResult result
			,Model model,RedirectAttributes flash, SessionStatus statu) {
	
		if(result.hasErrors()) {
			
			model.addAttribute("proveedores", proveedorService.findAll());
			model.addAttribute("lotes", loteService.findAll());
			model.addAttribute("dibujos", dibujoService.findAll());
			model.addAttribute("composiciones", composicionService.findAll());
			model.addAttribute("clientes", clienteService.findAll());
			
			model.addAttribute("titulo", "Formulario de detalles Clientes");
			return "contentClie/formcontentClient";
		}
		
		// Para saber si fue Creado o Editado
	    String mensajeFlash = (contentClient.getId() != null) ? "Guardado con Exito" : "Editado con Exito";

		
		contentClientService.save(contentClient);
		statu.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listcontentClie";
	}

	
	
	@GetMapping("/formcontentClient/{id}")
	public String editarcontentClient(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
		ContentClient contentClient = null;
		
		
		if(id>0) {
			contentClient = contentClientService.findOne(id);
			if (contentClient == null) {
				flash.addFlashAttribute("error", "El id del Cliente no Existe en la BD");
				return "redirect:/listcontentClie";
			}

		} else {
			flash.addFlashAttribute("error", "El id del cliente  no puede ser Cero");
		
			return "redirect:/listcontentClie";
		}
		
		model.addAttribute("proveedores", proveedorService.findAll());
		model.addAttribute("lotes", loteService.findAll());
		model.addAttribute("dibujos", dibujoService.findAll());
		model.addAttribute("composiciones", composicionService.findAll());
		model.addAttribute("clientes", clienteService.findAll());
		
		
		 model.addAttribute("contentClient", contentClient);
		 model.addAttribute("titulo", "Formulario de detalles Clientes");
		
		 return "contentClie/formcontentClient";
	}
		
	
	@RequestMapping(value = "/eliminarcontentClie/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {

			//solo se pone en cero
			contentClientService.fetchByIdConcentClient(id);
			flash.addFlashAttribute("success", "Registro Eliminado con Exito");
		}

		return "redirect:/listcontentClie";
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	