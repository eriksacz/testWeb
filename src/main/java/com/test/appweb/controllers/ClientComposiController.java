package com.test.appweb.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.test.appweb.models.entity.ClientComposi;
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.service.IClientComposiService;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IComposicionService;

@Controller
@SessionAttributes("clientComposi")
public class ClientComposiController {
	
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IClientComposiService clieCompoService;
	
	@Autowired
	private IComposicionService composiService;
	
	@GetMapping("/formclieComposi/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, 
			Map<String,Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteid);
		
		
		if(cliente == null) {
			flash.addFlashAttribute("error","El Cliente no existe");
		return "redirect:/listarclie";
	}
		
		ClientComposi clientComposi = new ClientComposi();
		clientComposi.setCliente(cliente);
		
		model.put("cliente", cliente);
		model.put("clientComposi", clientComposi);
		model.put("composiciones", composiService.findAll());
		model.put("titulo", "Formulario Cliente - Composiciones");
		
		return "clieComposi/formclieComposi";
	}
	
	
	@PostMapping("/formclieComposi")
	public String guardar(ClientComposi clientComposi, SessionStatus status,RedirectAttributes flash) {
		
		clieCompoService.save(clientComposi);
			status.setComplete();
			flash.addFlashAttribute("success", "Composiciones Asignado Correctamente");
		//return "redirect:/verclie/" + clientComposi.getCliente().getId();
		return "redirect:/formclieComposi/" + clientComposi.getCliente().getId();
	}

	
	

	@RequestMapping(value = "/eliminarclieComposi/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

	ClientComposi clientComposi = null;
		
		if (id > 0) {

			clieCompoService.fetchByIdClientComposi(id);
			flash.addFlashAttribute("success", "Composición Eliminado con Exito");
		}
		
		clientComposi = clieCompoService.findOne(id);
		
		return "redirect:/verclie/" + clientComposi.getCliente().getId();

	}

}
