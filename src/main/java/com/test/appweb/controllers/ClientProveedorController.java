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
import com.test.appweb.models.entity.ClientProveedor;
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.service.IClientProveedorService;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IProveedorService;

@Controller
@SessionAttributes("clientProveedor")
public class ClientProveedorController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IClientProveedorService clientProveedorService;
	
	@Autowired
	private IProveedorService proveedorService;
	
	@GetMapping("/formclieProveedor/{clienteid}")
	public String crear(@PathVariable(value="clienteid") Long clienteid, 
			Map<String,Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(clienteid);
		
		
		if(cliente == null) {
			flash.addFlashAttribute("error","El Cliente no existe");
		return "redirect:/listarclie";
	}
		
		ClientProveedor clienProveedor = new ClientProveedor();
		clienProveedor.setCliente(cliente);
		
		model.put("cliente", cliente);
		model.put("clienProveedor", clienProveedor);
		model.put("proveedores", proveedorService.findAll());
		model.put("titulo", "Formulario Cliente - Lote");
		
		return "clieProveedor/formclieProveedor";
	}
	
	@PostMapping("/formclieProveedor")
	public String guardar(ClientProveedor clientProveedor, SessionStatus status,RedirectAttributes flash) {
		
		clientProveedorService.save(clientProveedor);
			status.setComplete();
			flash.addFlashAttribute("success", "Proveedor Asignado Correctamente");
		//return "redirect:/verclie/" + clientProveedor.getCliente().getId();
		return "redirect:/formclieProveedor/" + clientProveedor.getCliente().getId();
	}

	// Para ELIMINAR
	@RequestMapping(value = "/eliminarclieProveedor/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		ClientProveedor clientProveedor = null;
		
		if (id > 0) {

			clientProveedorService.fetchByIdClientProveedor(id);
			flash.addFlashAttribute("success", "Proveedor Eliminado con Exito");
		}
		
		clientProveedor = clientProveedorService.findOne(id);
		
		return "redirect:/verclie/" + clientProveedor.getCliente().getId();

	}
	
}
