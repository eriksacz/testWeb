package com.test.appweb.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.test.appweb.controllers.util.paginator.PageRender;
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	
	
	@GetMapping(value = "/verclie/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
	
		Cliente cliente = clienteService.findOne(id);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "El Cliente no Existe en La Base de Datos");
			return "redirect:/listarclie";
		}

		model.put("cliente", cliente);
		model.put("titulo", "Detalle Cliente: " + cliente.getCliente());

		
	return "verclie";
	}
	
	// Para LISTAR
		@RequestMapping(value = "/listarclie", method = RequestMethod.GET)
		public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

			Pageable pageRequest = PageRequest.of(page, 4);

			Page<Cliente> clientes = clienteService.findAll(pageRequest);

			PageRender<Cliente> pageRender = new PageRender<>("/listarclie", clientes);

			model.addAttribute("titulo", "Listado de Clientes");
			model.addAttribute("clientes", clientes);
			
			

			// Se pasa a la vista el paguinador
			model.addAttribute("page", pageRender);

			return "listarclie";
		}
		
		// Para CREAR
		@RequestMapping(value = "/formclie")
		public String crear(Map<String, Object> model) {
		
		Cliente cliente = new Cliente();
		
		model.put("titulo", "Formulario de Clientes");
		model.put("cliente", cliente);

		return "formclie";
		}
		
		
		// Para EDITAR
		@RequestMapping(value = "/formclie/{id}")
		public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

			Cliente cliente  = null;
			
			if (id > 0) {
				cliente = clienteService.findOne(id);

				if (cliente == null) {
					flash.addFlashAttribute("error", "El id del Cliente no Existe en la BD");
					return "redirect:/listarclie";
				}

			} else {
				flash.addFlashAttribute("error", "El id del Cliente no puede ser Cero");
				return "redirect:/listarclie";
			}

			model.put("cliente", cliente);
			model.put("titulo", "Editar Cliente");

			return "formclie";
		}
		
		// Para GUARDAR * Despues del model se agrega lo se la session esto se hace para
		// editar
		@RequestMapping(value = "/formclie", method = RequestMethod.POST)
		public String guardar(@Valid Cliente cliente, BindingResult result, Model model
										, RedirectAttributes flash, SessionStatus status) {
			
			 cliente.setActivo((long) 1);
		
			if (result.hasErrors()) {
				model.addAttribute("titulo", "Formulario de Clientes");
				return "formclie";
			}

			// Para saber si fue Creado o Editado
			String mensajeFlash = (cliente.getId() != null) ? "Cliente Editado con Exito" : "Cliente Creado con Exito";

			clienteService.save(cliente);

			
			// Aqui se elimina la sesion despues de guardar
			status.setComplete();

			flash.addFlashAttribute("success", mensajeFlash);
			
			return "redirect:listarclie";
		}
		
		// Para ELIMINAR
		@RequestMapping(value = "/eliminarclie/{id}")
		public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				clienteService.fetchByIdCliente(id);
				//clienteService.delete(id);
				flash.addFlashAttribute("success", "Cliente Eliminado con Exito");
			}

			return "redirect:/listarclie";
		}
		
		
}
