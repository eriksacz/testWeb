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
import com.test.appweb.models.entity.ClientLot;
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.service.IClientLotService;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.ILoteService;

@Controller
@SessionAttributes("clientLot")
public class ClientLotController {
	
	@Autowired
	private  IClientLotService clieLoteService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ILoteService loteService;
	

	
	@GetMapping("/formclieLot/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, 
			Map<String,Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteid);
		
		
		if(cliente == null) {
			flash.addFlashAttribute("error","El Cliente no existe");
		return "redirect:/listarclie";
	}
		
		ClientLot clientLot = new ClientLot();
		clientLot.setCliente(cliente);
		
		model.put("cliente", cliente);
		model.put("clientLot", clientLot);
		model.put("lotes", loteService.findAll());
		model.put("titulo", "Formulario Cliente - Lote");
		
		return "clieLot/formclieLot";
	}
	
	@PostMapping("/formclieLot")
	public String guardar(ClientLot clientLot, SessionStatus status,RedirectAttributes flash) {
		
		clieLoteService.save(clientLot);
			status.setComplete();
			flash.addFlashAttribute("success", "Lote Asignado Correctamente");
	//	return "redirect:/verclie/" + clientLot.getCliente().getId();
		return "redirect:/formclieLot/" + clientLot.getCliente().getId();
	}

	// Para ELIMINAR
	@RequestMapping(value = "/eliminarclieLot/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

	ClientLot clientLot = null;
		
		if (id > 0) {

			clieLoteService.fetchByIdClientLot(id);
			flash.addFlashAttribute("success", "Lote Eliminado con Exito");
		}
		
		clientLot = clieLoteService.findOne(id);
		
		return "redirect:/verclie/" + clientLot.getCliente().getId();

	}



}
