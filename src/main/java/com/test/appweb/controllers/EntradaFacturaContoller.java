package com.test.appweb.controllers;

import java.util.Map;

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
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IEntradaFacturaService;

@Controller
@SessionAttributes("entradaFactura")
public class EntradaFacturaContoller {
	
	
	@Autowired
	private IEntradaFacturaService entradaFacturaService;
	
	@Autowired
	private IClienteService clienteService;
	
	 @GetMapping("/listentradaFactura") //MUESTRA TODAS LAS FACTURAS EN UNA TABLA GENERAL
		public String listarcompo(Model model) {
	    	model.addAttribute("titulo", "Lista de Detalle de Facturas");
	    	model.addAttribute("entradaFactura", entradaFacturaService.findAll());
			return "entradaFactura/listentradaFactura";
	}

	 
	 @GetMapping(value = "/facDetalle/{id}") //MUESTRA EL DETALLE DE UNA FACTURA POR CLIENTE
		public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
			EntradaFactura facdetalle = entradaFacturaService.findOne(id);
			
			if (facdetalle == null) {
				flash.addFlashAttribute("error", "La factura no Existe en La Base de Datos");
				return "listarCliente";
			}

			model.put("facdetalle", facdetalle);
			model.put("titulo", "Detalle Factura: " + facdetalle.getCliente().getCliente());

			
		return "entradaFactura/facDetalle";
		} 
	 
		@GetMapping("/formentradafactura/{clienteId}") //CREA EL FORMULARIO PARA CREAR UNA NUEVA FACTURA
		public String crear(@PathVariable(value = "clienteId") Long clienteId, Map<String, Object> model,
				RedirectAttributes flash) {

			Cliente cliente = clienteService.findOne(clienteId);

				
		  if (cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
				return "redirect:/listar";
			}
		 	
			EntradaFactura entradaFactura = new EntradaFactura();
			entradaFactura.setCliente(cliente);

			model.put("entradaFactura", entradaFactura);
			model.put("cliente", cliente);
			model.put("titulo", "Crear Factura");

			return "entradaFactura/formentradasFactura";
		}
	 

		@PostMapping("/formentradasFactura")
		public String guardarentradaFactura(@Valid EntradaFactura entradaFactura, BindingResult result
				,Model model,RedirectAttributes flash, SessionStatus statu) {
		
			if(result.hasErrors()) {
						
				model.addAttribute("titulo", "Formulario Entradas Facturas");
				return "entradaFactura/formentradasFactura";
			}
			
			// Para saber si fue Creado o Editado
		    String mensajeFlash = (entradaFactura.getId() != null) ? "Editado con Exito" : "Guardado con Exito";

			
			entradaFacturaService.save(entradaFactura);
			statu.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:listentradaFactura";
		}

	
		
		@GetMapping("/formentradasFactura2/{id}")
		public String editarentradaFactura(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
			EntradaFactura entradaFactura = null;
			
			
			if(id>0) {
				entradaFactura = entradaFacturaService.findOne(id);
				
				if (entradaFactura == null) {
					flash.addFlashAttribute("error", "El id no Existe en la BD");
					return "redirect:/listentradaFactura";
				}

			} else {
				flash.addFlashAttribute("error", "El id  no puede ser Cero");
				return "redirect:/listentradaFactura";
			}
			

			
			model.addAttribute("entradaFactura", entradaFactura);
			model.addAttribute("titulo", "Formulario Editar Entradas Facturas");
			
			 return "entradaFactura/formentradasFactura2";
		}
			
		
	
		// Para ELIMINAR
		@RequestMapping(value = "/eliminarfactura/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero
				entradaFacturaService.fetchByIdentradaFactura(id);
				flash.addFlashAttribute("success", "Factura Eliminada con Exito");
			}

			return "redirect:/listentradaFactura";
		}
		
			
		
		@RequestMapping(value = "/fuerafactura/{id}")
		public String fuera(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero

				entradaFacturaService.fetchByIdentradaFacturados(id);
				flash.addFlashAttribute("error", "Factura Concluida");
			}

			return "redirect:/listentradaFactura";
		}
		
		@RequestMapping(value = "/serviciofactura/{id}")
		public String servicio(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en uno
				entradaFacturaService.fetchByIdentradaFacturatres(id);
				flash.addFlashAttribute("success", "En proceso..");
			}

			return "redirect:/listentradaFactura";
		}

		
		
		
		
		
		
		
	 
}
