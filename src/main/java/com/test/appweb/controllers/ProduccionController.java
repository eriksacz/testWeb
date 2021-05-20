package com.test.appweb.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
import com.test.appweb.models.entity.Cliente;
import com.test.appweb.models.entity.DibuFac;
import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.entity.Produccion;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IDibujoFacService;
import com.test.appweb.models.service.IDibujoService;
import com.test.appweb.models.service.IEmpleadoService;
import com.test.appweb.models.service.IEntradaFacturaService;
import com.test.appweb.models.service.IMaquinaService;
import com.test.appweb.models.service.IProduccionService;
import com.test.appweb.models.service.ITurnoService;

@Controller
@SessionAttributes("produccion")
public class ProduccionController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IMaquinaService maquinaService;

	@Autowired
	private IEntradaFacturaService entradaFacturaService;

	@Autowired
	private IDibujoFacService dibuFacService;

	@Autowired
	private IDibujoService dibujoService; 

	@Autowired
	private IProduccionService produccionService;
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private ITurnoService turnoService;
	

	/*
	 * se selecciona cliente y se pasa id
	 */

	@RequestMapping(value = "/formproduccion1")
	public String crear(Map<String, Object> model) {

		model.put("titulo", "Agregar Producción Para el Cliente");
		model.put("clientes", clienteService.findAll());

		return "produccion/formproduccion1";
	}

	/*
	 * se obtiene id cliente y se imprimen sus facturas i se manda id factura
	 */

	@GetMapping(value = "/formproduccion2/{id}")
	public String crear2(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/formproduccion1";
		}

		model.put("titulo", "Agregar Producción Para La Factura");
		model.put("clientes", cliente);

		return "produccion/formproduccion2";
	}

	/*
	 * se obtien el id de la factura
	 */

	@GetMapping(value = "/formproduccion3/{id}")
	public String crear3(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		EntradaFactura entradaFactura = entradaFacturaService.findOne(id);

		if (entradaFactura == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/formproduccion1";
		}

		model.put("titulo", "Agregar Producción Para La Factura");
		model.put("entradaFacturas", entradaFactura);

		return "produccion/formproduccion3";
	}

	@GetMapping(value = "/formproduccion4/{id}")
	public String crear4(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		DibuFac dibuFac = dibuFacService.findOne(id);

		if (dibuFac == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/formproduccion1";
		}

		model.put("titulo", "Agregar Producción Para La Factura");
		model.put("dibuFacs", dibuFac);
		return "produccion/formproduccion4";
	}

	@GetMapping("/formproduccion6/{entradaFactura}/{maquina}/{dibujo}")
	public String crear6(
			@PathVariable(value = "entradaFactura") EntradaFactura entradaFactura,
			@PathVariable(value = "entradaFactura") Long entradaFactu,
			@PathVariable(value = "dibujo") Dibujo dibujo,
			@PathVariable(value = "dibujo") Long dibu,
			@PathVariable(value = "maquina") Long maqui, 
			@PathVariable(value = "maquina") Maquina maquina,
			Map<String, Object> model, RedirectAttributes flash) throws ParseException {

		List<Produccion> produccionrollo = produccionService.fetchByrollo(entradaFactura, maquina, dibujo);

		if (produccionrollo.size() == 0) {

			Dibujo dibuFac = dibujoService.findOne(dibu);

			Maquina facDibuMaqui = maquinaService.findOne(maqui);

			EntradaFactura entradafac = entradaFacturaService.findOne(entradaFactu);

			model.put("produccionrollos", 1);
			model.put("titulo", "Agregar Producción Para La Factura");
			model.put("dibuFacs", dibuFac);
			model.put("facDibuMaquis", facDibuMaqui);
			model.put("entradafacs", entradafac);

			return "redirect:/formproduccion5/" + entradaFactu + "/" +  maqui + "/" + dibu;

		} else {
			 Calendar fechas = new GregorianCalendar();
			 
			 int año = fechas.get(Calendar.YEAR);
		     int mes = fechas.get(Calendar.MONTH);
		     int dia = fechas.get(Calendar.DAY_OF_MONTH);
		     
		     String fechass = año + "-" + (mes+1) + "-" + dia;
		    
		     DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd");
			 Date fecha = fechaHora.parse(fechass);
		   
		     
			 List<Produccion> fechax =  produccionService.fetchByProduccion(entradaFactura, maquina, dibujo, fecha);
			
			Dibujo dibuFac = dibujoService.findOne(dibu);

			Maquina facDibuMaqui = maquinaService.findOne(maqui);

			EntradaFactura entradafac = entradaFacturaService.findOne(entradaFactu);

			Produccion ultimorollo = produccionrollo.get(produccionrollo.size() - 1);

			var ultimo = ultimorollo.getRollo() + 1;
			
			Produccion produccion = new Produccion();
			produccion.setActivo((long) 1);
			produccion.setRollo(ultimo);

			model.put("produccionrollos", ultimo);
			model.put("titulo", "Agregar Producción Para La Factura");
		
			model.put("dibuFacs", dibuFac);
			model.put("dibujo", dibujo);
			
			model.put("facDibuMaquis", facDibuMaqui);
			model.put("maquina", maquina);
			
			model.put("entradafacs", entradafac);
			model.put("entradafactura", entradaFactura);
			
			model.put("empleados", empleadoService.findAll());
			model.put("turnos", turnoService.findAll());
			
			model.put("fechax", fechax);
			
			model.put("produccion", produccion);

			return "produccion/formproduccion6";
		}
	}
	//Cuando esta en cero la produccion
	@GetMapping("formproduccion5/{entradaFactura}/{maquina}/{dibujo}")
	public String crear5(
			@PathVariable(value = "entradaFactura") EntradaFactura entradaFactura,
			@PathVariable(value = "entradaFactura") Long entradaFactu,
			@PathVariable(value = "dibujo") Dibujo dibujo,
			@PathVariable(value = "dibujo") Long dibu,
			@PathVariable(value = "maquina") Long maqui, 
			@PathVariable(value = "maquina") Maquina maquina,
			Map<String, Object> model, RedirectAttributes flash) {

		Dibujo dibuFac = dibujoService.findOne(dibu);

		Maquina facDibuMaqui = maquinaService.findOne(maqui);

		EntradaFactura entradafac = entradaFacturaService.findOne(entradaFactu);
		
		Produccion produccion = new Produccion();
		
		produccion.setActivo((long) 1);
		produccion.setRollo((long) 1);
		
		model.put("produccionrollos", 1);
		model.put("titulo", "Agregar Producción Para La Factura");
		
		model.put("dibuFacs", dibuFac);
		model.put("dibujo", dibujo);
		
		model.put("facDibuMaquis", facDibuMaqui);
		model.put("maquina", maquina);
		
		model.put("entradafacs", entradafac);
		model.put("entradafactura", entradaFactura);
		
		model.put("empleados", empleadoService.findAll());
		model.put("turnos", turnoService.findAll());
		
		model.put("produccion", produccion);

		return "produccion/formproduccion5";
	}
	

	@GetMapping("/formproduccion8/{id}")
	public String crear7(@PathVariable(value="id")Long id,
			Map<String, Object> model, RedirectAttributes flash) {
		
		Produccion produccion = null;
		
		if(id>0) {
			
			produccion = produccionService.findOne(id);
			
			if (produccion == null) {
				flash.addFlashAttribute("error", "El id no Existe en la BD");
				return "redirect:/formproduccion1";
			}

		} else {
			flash.addFlashAttribute("error", "El id  no puede ser Cero");
		
			return "redirect:/formproduccion1";
		}
		
		
		
		model.put("titulo", "Editar Registro de Producción");
		
		model.put("empleados", empleadoService.findAll());
		model.put("turnos", turnoService.findAll());
		
		model.put("produccion", produccion);

		return "produccion/formproduccion8";
	}
	

	
	@PostMapping("/formproduccion7")
	public String guardar(Produccion produccion, SessionStatus status,RedirectAttributes flash) {

		 String mensajeFlash = (produccion.getId() != null) ? "Editado con Exito" : "Guardado con Exito";
	
			produccionService.save(produccion);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/formproduccion6/"+ produccion.getEntradaFactura().getId() +"/"+ produccion.getMaquina().getId() 
				+"/"+ produccion.getDibujo().getId();
	}
	
	
	@RequestMapping(value = "/eliminar123/{id}")
	public String eliminar123(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Produccion produccion = produccionService.findOne(id);
		
		if (id > 0) {
		
			produccionService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito!");

		}
		return "redirect:/formproduccion6/"+ produccion.getEntradaFactura().getId() +"/"+ produccion.getMaquina().getId() 
				+"/"+ produccion.getDibujo().getId();
	}
	
}
