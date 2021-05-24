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

import com.test.appweb.models.entity.ClientDibu;
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.service.IClientDibuService;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IDibujoService;

@Controller
@SessionAttributes("clientDibu")
public class ClientDibuController {
	
	@Autowired
	private IClientDibuService clientDibuService;

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IDibujoService dibujoService;
	
	@GetMapping("/formclieDibu/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, 
			Map<String,Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteid);
		
		
		if(cliente == null) {
			flash.addFlashAttribute("error","El Cliente no existe");
		return "redirect:/listarclie";
	}
		
		ClientDibu clientDibu = new ClientDibu();
		clientDibu.setCliente(cliente);
		
		model.put("cliente", cliente);
		model.put("clientDibu", clientDibu);
		model.put("dibujos", dibujoService.findAll());
		model.put("titulo", "Formulario Cliente - Dibujo");
		
		return "clieDibu/formclieDibu";
	}
	
	@PostMapping("/formclieDibu")
	public String guardar(ClientDibu clientDibu, SessionStatus status,RedirectAttributes flash) {
		clientDibu.setActivo((long) 1);
		clientDibuService.save(clientDibu);	
		status.setComplete();
			flash.addFlashAttribute("success", "dibujo Asignado Correctamente");
		return "redirect:/formclieDibu/" + clientDibu.getCliente().getId();
	}

	// Para ELIMINAR
	@RequestMapping(value = "/eliminarclieDibu/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

	ClientDibu clientDibu = null;
		
		if (id > 0) {

			clientDibuService.fetchByIdClientDibu(id);
			flash.addFlashAttribute("success", "Dibujo Eliminado con Exito");
		}
		
		clientDibu = clientDibuService.findOne(id);
		
		return "redirect:/verclie/" + clientDibu.getCliente().getId();

	}

	
}
