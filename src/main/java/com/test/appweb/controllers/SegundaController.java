package com.test.appweb.controllers;

import java.text.ParseException;
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
import com.test.appweb.models.entity.Segunda;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IDibujoFacService;
import com.test.appweb.models.service.IEmpleadoService;
import com.test.appweb.models.service.IEntradaFacturaService;
import com.test.appweb.models.service.IProduccionService;
import com.test.appweb.models.service.ISegundaService;
import com.test.appweb.models.service.ITurnoService;

@Controller
@SessionAttributes("segunda")
public class SegundaController {
	
	@Autowired
	private IClienteService clienteService;
	
	/**
	 * Se Listan los clientes y se pasa el id del cliente
	 *
	 */
	@RequestMapping(value = "/listarClienteSegunda")
	public String crear11(Map<String, Object> model) {

		model.put("titulo", "Segunda Para el Cliente");
		model.put("clientes", clienteService.findAll());

		return "segunda/listarClienteSegunda";
	}
	
	/**
	 * Se busca el cliente por el id que se pasa del metodo anterioir se busca en
	 * facturas Cuando coincide con el Cliente se Pasa el id de la factura
	 */
	@GetMapping(value = "/listarClieFacturasSegunda/{id}")
	public String crear22(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSegunda";
		}

		model.put("titulo", "Segunda Para La Factura");
		model.put("clientes", cliente);

		return "segunda/listarClieFacturasSegunda";
	}

	@Autowired
	private IEntradaFacturaService entradaFacturaService;
	
	/**
	 * @param id    se pasa el id de la factura y se busca en dibu_facturas para
	 *              listar los dibujos
	 * @param model
	 * @param flash
	 * @return
	 */
	@GetMapping(value = "/listarDibuFacSegunda/{id}")
	public String crear33(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		EntradaFactura entradaFactura = entradaFacturaService.findOne(id);

		if (entradaFactura == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSegunda";
		}

		model.put("titulo", "Segunda con Dibujo");
		model.put("entradaFacturas", entradaFactura);

		return "segunda/listarDibuFacSegunda";
	}
	
	
	@Autowired
	private IDibujoFacService dibuFacService;
	
	/**
	 * 
	 * @param id    se pasa el id de dibuFac y se busca la maquina que hizo ese dibujo
	 * @param model
	 * @param flash
	 * @return
	 */
	@GetMapping(value = "/listarDibuFacMaquinaSegunda/{id}")
	public String crear4(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		DibuFac dibuFac = dibuFacService.findOne(id);

		if (dibuFac == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSegunda";
		}

		model.put("titulo", "Segunda de la Máquina");
		model.put("dibuFacs", dibuFac);
		return "segunda/listarDibuFacMaquinaSegunda";
	}
	
	
	@Autowired
	private IProduccionService produccionService;
	
	@GetMapping("/totalproduccion/{entradaFactura}/{maquina}/{dibujo}")
	public String crear(@PathVariable(value = "entradaFactura") EntradaFactura entradaFactura,
			 @PathVariable(value = "dibujo") Dibujo dibujo,
			@PathVariable(value = "dibujo") Long dibu,
			@PathVariable(value = "maquina") Maquina maquina,
			Map<String, Object> model, RedirectAttributes flash)
			throws ParseException {

		List<Produccion> produccion = produccionService.fetchByrollo(entradaFactura, maquina, dibujo);

		if (produccion.size() == 0) {
			
			 String mensajeFlash = "Esta Máquina No Tiene Producción para Tela de Segunda";
			flash.addFlashAttribute("success", mensajeFlash);
			
			return "redirect:/listarDibuFacMaquinaSegunda/" + dibu;
		} else {

			
			model.put("titulo", "Agregar a Segunda");

			model.put("produccion", produccion);

			return "segunda/totalproduccion";

		}
	}
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private ITurnoService turnoService;
	
	@SuppressWarnings("null")
	@GetMapping(value = "/formSegunda/{id}")
	public String crearSegunda(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Produccion produccion = produccionService.findOne(id);

		if (produccion == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarDibuFacMaquinaSegunda/" + produccion.getDibujo().getId();
		}

		Segunda segunda = new Segunda();
		
		model.put("titulo", "Agregar a Segunda");

		model.put("empleados", empleadoService.findAll());
		model.put("turnos", turnoService.findAll());

		model.put("produccion", produccion);
		model.put("segunda", segunda);

		return "segunda/formSegunda";
	}

	@Autowired
	private ISegundaService segundaService;
	
	@PostMapping("/formSegunda")
	public String guardar(Segunda segunda, SessionStatus status,RedirectAttributes flash) {

		 String mensajeFlash = (segunda.getId() != null) ? "Editado con Exito" : "Guardado con Exito";
	
		 segundaService.save(segunda);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/listarDibuFacSegunda/"+ segunda.getEntradaFactura().getId();
	}
	
}
