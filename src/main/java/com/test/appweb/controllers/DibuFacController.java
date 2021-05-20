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
import com.test.appweb.models.entity.DibuFac;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.service.IDibujoFacService;
import com.test.appweb.models.service.IEntradaFacturaService;

@Controller
@SessionAttributes("dibuFac")
public class DibuFacController {
	
	@Autowired
	private IDibujoFacService dibujoFacService;
	
	@Autowired
	private IEntradaFacturaService entradaFacturaService;
	
	
	@GetMapping("/dibuFac1/{id}")
	public String crear(@PathVariable(value="id") Long id, 
			Map<String,Object> model, RedirectAttributes flash) {

		EntradaFactura entradaFactura = entradaFacturaService.findOne(id);
		
		
		if(entradaFactura == null) {
			flash.addFlashAttribute("error","La Factura no existe");
		return "redirect:/listarclie";
	}
		
		DibuFac dibuFac = new DibuFac();
		dibuFac.setEntradaFactura(entradaFactura);
		dibuFac.setActivo((long) 1);
		
		model.put("entradaFactura", entradaFactura);
		model.put("dibuFac", dibuFac);
	
		model.put("titulo", "Agregar Dibujo - Factura");
		
		return "dibuFac/dibuFactura";
	}
	
	
	@PostMapping("/dibuFac1")
	public String guardar(DibuFac dibuFac, SessionStatus status,RedirectAttributes flash) {
		
		dibujoFacService.save(dibuFac);
			status.setComplete();
			flash.addFlashAttribute("success", "Lote Asignado Correctamente");
			return "redirect:/dibuFac1/" + dibuFac.getEntradaFactura().getId();
		//return "redirect:/verclie/" + dibuFac.getEntradaFactura().getCliente().getId();
	}
	
	@RequestMapping(value = "/eliminardibufac/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		DibuFac dibuFac = null;
		
		if (id > 0) {

			dibujoFacService.fetchByIdDibuFac(id);
			flash.addFlashAttribute("success", "Dibujo Eliminado con Exito");
		}
		
		dibuFac = dibujoFacService.findOne(id);
		
		return "redirect:/dibuFac1/" + dibuFac.getEntradaFactura().getId();

	}
	
}
