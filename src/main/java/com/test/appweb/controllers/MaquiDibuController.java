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
import com.test.appweb.models.entity.MaquiDibu;
import com.test.appweb.models.service.IDibujoService;
import com.test.appweb.models.service.IMaquiDibuService;
import com.test.appweb.models.service.IMaquinaService;


@Controller
@SessionAttributes("maquiDibu")
public class MaquiDibuController {
	
	@Autowired
	private IMaquiDibuService maquiDibuService; 
	
	@Autowired
	private IDibujoService dibujoService;
	
	@Autowired
	private IMaquinaService maquinaService;
	
	 @GetMapping("/listmaquidibu")
		public String listarmaquidibu(Model model) {
	    	model.addAttribute("titulo", "Lista de Máquinas con  Dibujos");
	    	model.addAttribute("maquidibus", maquiDibuService.findAll());
	    	
			return "maquidibu/listmaquidibu";
	}
	
		@GetMapping("/formmaquidibu")
		public String crarcontentClient(Model model) {
		 
			MaquiDibu maquiDibu = new MaquiDibu();

			model.addAttribute("maquiDibu", maquiDibu);
			model.addAttribute("dibujos", dibujoService.findAll());
			model.addAttribute("maquinas", maquinaService.findAll());
			model.addAttribute("titulo", "Formulario de Máquinas Con Dibujos");
				
			
		 return "maquidibu/formmaquidibu";
		}
		
		
		@PostMapping("/formmaquidibu")
		public String guardarcontentClient(@Valid MaquiDibu maquiDibu, BindingResult result
				,Model model,RedirectAttributes flash, SessionStatus statu) {
		
			if(result.hasErrors()) {
				
				model.addAttribute("dibujos", dibujoService.findAll());
				model.addAttribute("maquinas", maquinaService.findAll());
				model.addAttribute("titulo", "Formulario de Máquinas Con Dibujos");
				 return "maquidibu/formmaquidibu";
			}
			
			// Para saber si fue Creado o Editado
		    String mensajeFlash = (maquiDibu.getId() != null) ? "Guardado con Exito" : "Editado con Exito";

			
			maquiDibuService.save(maquiDibu);
			statu.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);
			return "redirect:listmaquidibu";
		}
		
		@GetMapping("/formmaquidibu/{id}")
		public String editarmaquiDibu(@PathVariable(value="id")Long id, Model model, RedirectAttributes flash) {
			MaquiDibu maquiDibu = null;
			
			
			if(id>0) {
				
				maquiDibu = maquiDibuService.findOne(id);
				
				if (maquiDibu == null) {
					flash.addFlashAttribute("error", "El id no Existe en la BD");
					return "redirect:/listmaquidibu";
				}

			} else {
				flash.addFlashAttribute("error", "El id  no puede ser Cero");
			
				return "redirect:/listmaquidibu";
			}

			model.addAttribute("maquiDibu", maquiDibu);
			model.addAttribute("dibujos", dibujoService.findAll());
			model.addAttribute("maquinas", maquinaService.findAll());
			model.addAttribute("titulo", "Formulario de Máquinas Con Dibujos");
			
			 return "maquidibu/formmaquidibu";
		}
		
		@RequestMapping(value = "/fueramaquidibu/{id}")
		public String fuera(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

			if (id > 0) {

				//solo se pone en cero
				maquiDibuService.fetchByIdMaquiDibu(id);
				flash.addFlashAttribute("error", "Eliminado");
			}

			return "redirect:/listmaquidibu";
		}
		
			
}
