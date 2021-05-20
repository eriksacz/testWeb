package com.test.appweb.controllers;

import java.text.DecimalFormat;
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
import com.test.appweb.models.entity.Salida;
import com.test.appweb.models.service.IClienteService;
import com.test.appweb.models.service.IDibujoFacService;
import com.test.appweb.models.service.IDibujoService;
import com.test.appweb.models.service.IEntradaFacturaService;
import com.test.appweb.models.service.IMaquinaService;
import com.test.appweb.models.service.IProduccionService;
import com.test.appweb.models.service.ISalidaService;

@Controller
@SessionAttributes("salida")
public class SalidaController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IEntradaFacturaService entradaFacturaService;

	@Autowired
	private IDibujoFacService dibuFacService;

	@Autowired
	private IProduccionService produccionService;

	/**
	 * Se Listan los clientes y se pasa el id del cliente
	 *
	 */

	@RequestMapping(value = "/listarClienteSalida")
	public String crear1(Map<String, Object> model) {

		model.put("titulo", "Salida Para el Cliente");
		model.put("clientes", clienteService.findAll());

		return "salida/listarClienteSalida";
	}

	/**
	 * Se busca el cliente por el id que se pasa del metodo anterioir se busca en
	 * facturas Cuando coincide con el Cliente se Pasa el id de la factura
	 */
	@GetMapping(value = "/listarClieFacturasSalida/{id}")
	public String crear2(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSalida";
		}

		model.put("titulo", "Salida Para La Factura");
		model.put("clientes", cliente);

		return "salida/listarClieFacturasSalida";
	}

	/**
	 * @param id    se pasa el id de la factura y se busca en dibu_facturas para
	 *              listar los dibujos
	 * @param model
	 * @param flash
	 * @return
	 */
	@GetMapping(value = "/listarDibuFacSalida/{id}")
	public String crear3(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		EntradaFactura entradaFactura = entradaFacturaService.findOne(id);

		if (entradaFactura == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSalida";
		}

		model.put("titulo", "Salida con Dibujo");
		model.put("entradaFacturas", entradaFactura);

		return "salida/listarDibuFacSalida";
	}

	/**
	 * 
	 * @param id    se pasa el id de dibuFac y se busca la maquina que hizo ese
	 *              dibujo
	 * @param model
	 * @param flash
	 * @return
	 */

	@GetMapping(value = "/listarDibuFacMaquina/{id}")
	public String crear4(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		DibuFac dibuFac = dibuFacService.findOne(id);

		if (dibuFac == null) {
			flash.addFlashAttribute("error", "El iD no existe");
			return "redirect:/listarClienteSalida";
		}

		model.put("titulo", "Agregar Producción de la Máquina");
		model.put("dibuFacs", dibuFac);
		return "salida/listarDibuFacMaquina";
	}

	@Autowired
	private IDibujoService dibujoService;

	@Autowired
	private IMaquinaService maquinaService;

	@GetMapping("/formSalida/{entradaFactura}/{maquina}/{dibujo}")
	public String crear(@PathVariable(value = "entradaFactura") EntradaFactura entradaFactura,
			@PathVariable(value = "entradaFactura") Long entradaFactu, @PathVariable(value = "dibujo") Dibujo dibujo,
			@PathVariable(value = "dibujo") Long dibu, @PathVariable(value = "maquina") Long maqui,
			@PathVariable(value = "maquina") Maquina maquina, Map<String, Object> model, RedirectAttributes flash)
			throws ParseException {

		List<Produccion> produccionrollo = produccionService.fetchByrollo(entradaFactura, maquina, dibujo);

		Double kilos = 0.0;

		if (produccionrollo.size() == 0) {
			
			 String mensajeFlash = "Esta Máquina No Tiene nuevos Registros para Darle Salida";
			flash.addFlashAttribute("success", mensajeFlash);
			
			return "redirect:/listarDibuFacMaquina/" + dibu;
		} else {

			for (int i = 0; i < produccionrollo.size(); i++) {

				kilos += Double.parseDouble(produccionrollo.get(i).getKilo());

			}

			//Produccion ultimorollo = produccionrollo.get(produccionrollo.size() - 1);

			var ultimo = produccionrollo.size();

			Dibujo dibuFac = dibujoService.findOne(dibu);

			Maquina facDibuMaqui = maquinaService.findOne(maqui);

			EntradaFactura entradafac = entradaFacturaService.findOne(entradaFactu);

			Salida salida = new Salida();

			salida.setRollo((long) ultimo);
			
			

			DecimalFormat formato = new DecimalFormat("#.000");

			String cadena = String.valueOf(formato.format(kilos));

			salida.setKilo(cadena);

			model.put("produccionrollos", ultimo);

			model.put("kilos", cadena);

			model.put("titulo", "Salidas");

			model.put("dibuFacs", dibuFac);
			model.put("dibujo", dibujo);

			model.put("facDibuMaquis", facDibuMaqui);
			model.put("maquina", maquina);

			model.put("entradafacs", entradafac);
			model.put("entradafactura", entradaFactura);

			model.put("salida", salida);

			return "salida/formSalida";

		}

	}
	
	@Autowired
	private ISalidaService salidaService;
	
	@PostMapping("/formSalida")
	public String guardar(Salida salida, SessionStatus status,RedirectAttributes flash) {

		 String mensajeFlash = (salida.getId() != null) ? "Editado con Exito" : "Guardado con Exito";
	
			salidaService.save(salida);
			status.setComplete();
			flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/listarDibuFacSalida/"+ salida.getEntradaFactura().getId();
	}

}
