package com.test.appweb.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.appweb.models.entity.Perfil;
import com.test.appweb.models.entity.Usuario;
import com.test.appweb.models.service.IUsuariosService;



@Controller
public class HomeController {
	

	@Autowired
   	private IUsuariosService serviceUsuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * Método que muestra el formulario de login personalizado.
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin(@RequestParam(value="error", required=false)String error,
			@RequestParam(value="logout", required=false)String logout,
			Model model, Principal principal, RedirectAttributes flas) {
		
		if(principal != null) {
			flas.addFlashAttribute("info", "Ya Inicio Sesion Anteriormente");
			return "redirect:/xx";
		}
		
		if(error != null) {
			model.addAttribute("error", "Datos Incorrectos");
		}
		
		if(logout != null) {
			model.addAttribute("success", "Sesion Cerrada Con Exito");
		}
		
		return "loginprincipal";
	}
	

	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
	SecurityContextLogoutHandler logoutHandler =
	new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/login";
	}

	
	
	/**
	 * Método que muestra el formulario para que se registren nuevos usuarios.
	 * @param usuario
	 * @return
	 */
	@GetMapping("/registrarse")
	public String registrarse(Usuario usuario) {
		return "formusuarios";
	}
	
	
	@PostMapping("/signup")
	public String guardarRegistro(@Valid Usuario usuario, BindingResult result, 
			Model model,RedirectAttributes attributes) {
		
		
		if(result.hasErrors()) {
		
			return "formusuarios";
		}
		
		
		
		String pwdPlano = usuario.getPassword();
		String pwdEncriptada = passwordEncoder.encode(pwdPlano);
		
		usuario.setPassword(pwdEncriptada);
		
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFecha(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		Perfil perfil = new Perfil();// Creamos el Perfil que le asignaremos al usuario nuevo
		perfil.setId(usuario.getPerfilUsuario()); // Perfil USUARIO
		
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");
		
		return "redirect:/usuarios/index";
	
	}
	
	
		
		//METODO MIO PARA MADAR A INDEX
		@GetMapping("/xx")
		public String mostrarHome(Model model) {
			return "index";
		}
		
	
}
