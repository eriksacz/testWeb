package com.test.appweb.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.appweb.controllers.util.paginator.PageRender;
import com.test.appweb.models.entity.Empleado;
import com.test.appweb.models.service.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class EpleadoControler {

	@Autowired
	private IEmpleadoService empleadoService;

	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
	
	private final static String UPLOADS_FOLDER = "uploads";


	// Para cargar imagen desde http
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Path pathfoto = Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
		log.info("pathfoto:  " + pathfoto);

		Resource recurso = null;
		try {
			recurso = new UrlResource(pathfoto.toUri());
			if (!recurso.exists() || !recurso.isReadable()) {
				throw new RuntimeException("Error no se puede cargar la imagen " + pathfoto.toString());
			}
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		return ResponseEntity.ok().header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + recurso.getFilename() + "\"").body(recurso);
	}

	// Para Ver el detalle del Empleado y la foto
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Empleado empleado = empleadoService.findOne(id);
		if (empleado == null) {
			flash.addFlashAttribute("error", "El Empleado no Existe en La Base de Datos");
			return "redirect:/listar";
		}

		model.put("empleado", empleado);
		model.put("titulo", "Detalle Empleado: " + empleado.getNombre());

		return "ver";
	}

	// Para LISTAR
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		//Pageable pageRequest = PageRequest.of(page, 4, Sort.by("id").descending()); para listar descendente
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Empleado> empleados = empleadoService.findAll(pageRequest);

		PageRender<Empleado> pageRender = new PageRender<>("/listar", empleados);

		model.addAttribute("titulo", "Listado de Empleados");
		model.addAttribute("empleados", empleados);

		// Se pasa a la vista el paguinador
		model.addAttribute("page", pageRender);

		return "listar";
	}

	// Para CREAR
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Empleado empleado = new Empleado();
		
		model.put("titulo", "Formulario de Empleados");
		model.put("empleado", empleado);

		return "form";
	}

	// Para EDITAR
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Empleado empleado = null;

		if (id > 0) {
			empleado = empleadoService.findOne(id);

			if (empleado == null) {
				flash.addFlashAttribute("error", "El id del Empleado no Existe en la BD");
				return "redirect:/listar";
			}

		} else {
			flash.addFlashAttribute("error", "El id del Empleado no puede ser Cero");
			return "redirect:/listar";
		}

		model.put("empleado", empleado);
		model.put("titulo", "Editar Cliente");

		return "form";
	}

	// Para GUARDAR * Despues del model se agrega lo se la session esto se hace para
	// editar
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Empleados");
			return "form";
		}

		// Para foto
		if (!foto.isEmpty()) {
			
			//Para Eliminar Foto O Guardar Otra
			if(empleado.getId() != null 
					&& empleado.getId() > 0
					&& empleado.getFoto() != null
					&& empleado.getFoto().length() > 0 ) {
				Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(empleado.getFoto()).toAbsolutePath();
				File archivo = rootPath.toFile();
				if (archivo.exists() && archivo.canRead()) {
					archivo.delete();	
				}
			}

			String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
			Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFilename);
			Path rootAbsolutoPath = rootPath.toAbsolutePath();

			log.info("rootPath: " + rootPath);
			log.info("rootAbsolutoPath: " + rootAbsolutoPath);

			try {

				Files.copy(foto.getInputStream(), rootAbsolutoPath);
				flash.addFlashAttribute("info", "Foto Guardada correctamente" + uniqueFilename);
				empleado.setFoto(uniqueFilename);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Para saber si fue Creado o Editado
		String mensajeFlash = (empleado.getId() != null) ? "Empleado Editado con Exito" : "Empleado Creado con Exito";

		empleadoService.save(empleado);

		// Aqui se elimina la sesion despues de guardar
		status.setComplete();

		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:listar";
	}

	// Para ELIMINAR
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

	
		if (id > 0) {

			// Para eliminar foto *Obtenemos al empleado
			Empleado empleado = empleadoService.findOne(id);

			empleadoService.fetchByIdEmpleado(id);
			//empleadoService.delete(id);
			flash.addFlashAttribute("success", "Empleado Eliminado con Exito");

			if(empleado.getFoto() != null) {
			
			// Para eliminar foto
			Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(empleado.getFoto()).toAbsolutePath();
			
			File archivo = rootPath.toFile();
		
			if (archivo.exists() && archivo.canRead() && archivo != null) {
				if (archivo.delete()) {
					flash.addFlashAttribute("info", "Foto " + empleado.getFoto() + " eliminada con exito");
				}
			}
			}

		}

		return "redirect:/listar";
	}

}
