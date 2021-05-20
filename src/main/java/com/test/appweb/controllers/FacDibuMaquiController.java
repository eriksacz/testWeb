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
import com.test.appweb.models.entity.FacDibuMaqui;
import com.test.appweb.models.service.IDibujoFacService;
import com.test.appweb.models.service.IFacDibuMaquiService;

@Controller
@SessionAttributes("facDibuMaqui")
public class FacDibuMaquiController {
	
	@Autowired
	private IFacDibuMaquiService facDibuMaquiService;
	
	@Autowired
	private IDibujoFacService dibujoFacService;
	
	
	@GetMapping("/formfacDibuMaqui/{id}")
	public String crear(@PathVariable(value="id") Long id, 
			Map<String,Object> model, RedirectAttributes flash) {

		DibuFac dibuFac = dibujoFacService.findOne(id);
		
		
		if(dibuFac == null) {
			flash.addFlashAttribute("error","El iD no existe");
		return "redirect:/listarclie";
	}
		
		FacDibuMaqui facDibuMaqui = new FacDibuMaqui();
		facDibuMaqui.setDibuFac(dibuFac);
		facDibuMaqui.setActivo((long) 1);
		
		model.put("dibuFac", dibuFac);
		model.put("facDibuMaqui", facDibuMaqui);
	
		model.put("titulo", "Agregar Máquina - Dibujo");
		
		return "facDibuMaqui/formfacDibuMaqui";
	}
	
	@PostMapping("/formfacDibuMaqui")
	public String guardar(FacDibuMaqui facDibuMaqui, SessionStatus status,RedirectAttributes flash) {
		
		facDibuMaquiService.save(facDibuMaqui);
			status.setComplete();
			flash.addFlashAttribute("success", "Máquina Asignada Correctamente A dibujo");

		return "redirect:/formfacDibuMaqui/" + facDibuMaqui.getDibuFac().getId();
	}
	
	@RequestMapping(value = "/eliminarfacdibumaqui/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		FacDibuMaqui facDibuMaqui = null;
		
		if (id > 0) {

			facDibuMaquiService.fetchByIdFacDibuMaqui(id);
			flash.addFlashAttribute("success", "Lote Eliminado con Exito");
		}
		
		facDibuMaqui = facDibuMaquiService.findOne(id);
		
		return "redirect:/formfacDibuMaqui/" + facDibuMaqui.getDibuFac().getId();

	}	


}
