package com.test.appweb.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.entity.Empleado;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.entity.ProduConsulta;
import com.test.appweb.models.entity.Turno;
import com.test.appweb.models.service.IDibujoService;
import com.test.appweb.models.service.IEmpleadoService;
import com.test.appweb.models.service.IEntradaFacturaService;
import com.test.appweb.models.service.IMaquinaService;
import com.test.appweb.models.service.IProduConsultaService;
import com.test.appweb.models.service.ITurnoService;
import com.test.appweb.models.service.IreporteService;
import com.test.appweb.models.service.IreportedosService;
import com.test.appweb.models.entity.reportedos;
import com.test.appweb.models.entity.reporte;

@Controller
public class ProduConsultaController {

	@Autowired
	private IProduConsultaService produConsultaService;

	@Autowired
	private IEmpleadoService empleadoService;

	@Autowired
	private IEntradaFacturaService facturaService;

	@Autowired
	private IDibujoService dibujoService;

	@Autowired
	private IMaquinaService maquinaService;
	
	@Autowired
	private ITurnoService turnoService;
	
	@Autowired
	private IreportedosService reportedosService;
	
	@Autowired
	private IreporteService reporteService;

	@GetMapping("/menuconsulta")
	public String menuconsulta(Model model) {

		ProduConsulta produConsulta = new ProduConsulta();

		model.addAttribute("titulo", "Elija Una opción Para su reporte");
		model.addAttribute("produConsulta", produConsulta);

		return "produconsulta/menuconsulta";
	}

	@PostMapping("/menuconsulta")
	public String guardar(ProduConsulta produConsulta, SessionStatus status, RedirectAttributes flash) {

		String fechainicio = produConsulta.getFechainicio();
		String fechafin = produConsulta.getFechafin();

		produConsultaService.fetchByIntervalo(fechainicio, fechafin);

		return "produconsulta/listaFechas";
	}


	@GetMapping("/reporte")
	public String reporte(Model model) {
		
		reporteService.deleteMio();

		List<Empleado> cempleado = empleadoService.findAll();
		List<EntradaFactura> cfactura = facturaService.findAll();
		List<Dibujo> cdibujo = dibujoService.findAll();
		List<Maquina> cmaquina = maquinaService.findAll();
		List<Turno> cturno = turnoService.findAll();
		
		
		ArrayList<String> produConsult = new ArrayList<String>();

		for (int i = 0; i < cempleado.size(); i++) {
			for (int j = 0; j < cfactura.size(); j++) {
				for (int j2 = 0; j2 < cdibujo.size(); j2++) {
					for (int k = 0; k < cmaquina.size(); k++) {
						for (int k2 = 0; k2 < cturno.size(); k2++) {
							produConsult.addAll(produConsultaService.fetchByReporte(cempleado.get(i).getId(),
									cfactura.get(j).getId(), cdibujo.get(j2).getId(), cmaquina.get(k).getId(), cturno.get(k2).getId()));
						}
					}
				}
			}
		}


		String[][] matriz = new String[9889][13];
try {
	

		for (int i = 0; i < produConsult.size(); i++) {

			for (int j = 0; j < 13; j++) {

				String string = produConsult.get(i);

				String[] parts = string.split(",");

				matriz[i][j] = parts[j];

				System.out.println("Matriz1  -> " + matriz[i][j]);
			}

			System.out.println("Espacio  _____________________________");
		}
} catch (Exception e) {
	// TODO: handle exception
}
		int x = 0;
		String tot = null;
		double total = 0.0;
		String total2 = null;
		int rollo = 0;
		int rollototal = 0;
		String uno = null;
		String dos = null;
		for (int i = 0; i <= matriz.length; i++) {
		
			try {
				x = i + 1;
				
				uno = matriz[i][12];
				dos = matriz[x][12]; //nota  <------------------------------------
			
			if (dos == null) {
				break;
			}else {
				
			
			tot = matriz[i][11];
			double doble = Double.parseDouble(tot);
			
			if ( dos.equals(uno)) {
				System.out.println("cambia fecha " + matriz[i][12]);
				total2 = null;
				total = total + doble;
				rollo += 1;
			} else {
				System.out.println("fecha igual " + matriz[i][12]);
				total = total + doble;
				rollo += 1;
	
				BigDecimal formatNumber = new BigDecimal(total);
				formatNumber = formatNumber.setScale(2, RoundingMode.DOWN);
				
				total2 = String.valueOf(formatNumber);
				
				rollototal = rollo;
				
				total = 0.0;
				rollo = 0;
				System.out.println("total " + total2);
				System.out.println("rollo " + rollototal);
				
				
				String fechas = matriz[i][12];
				
				String roll = Integer.toString(rollototal);
				String totaal = String.valueOf(total2);
				
				String empleado = matriz[i][1];
				int emplead = Integer.parseInt(empleado);
				
				String dibujo = matriz[i][2];
				int dibu = Integer.parseInt(dibujo);
				
				String proveedor = matriz[i][3];
				int prov = Integer.parseInt(proveedor);
				
				String composicion = matriz[i][4];
				int compo = Integer.parseInt(composicion);
				
				String maquina = matriz[i][5];
				int maqui = Integer.parseInt(maquina);
				
				String lote = matriz[i][6];
				int lot = Integer.parseInt(lote);
				
				String turno = matriz[i][7];
				int tur = Integer.parseInt(turno);
				
				String factura = matriz[i][8];
				int fac = Integer.parseInt(factura);
				
				String cliente = matriz[i][9];
				int clie = Integer.parseInt(cliente);

				
				System.out.println( "Reporte  "+ emplead + dibu + prov + compo + maqui + lot + tur + fac + clie + roll + totaal + fechas);
				
				reportedos reporte = new reportedos();
				
				reporte.setEmpleado(emplead);
				reporte.setDibujo(dibu);
				reporte.setProveedor(prov);
				reporte.setComposicion(compo);
				reporte.setMaquina(maqui);
				reporte.setLote(lot);
				reporte.setTurno(tur);
				reporte.setFactura(fac);
				reporte.setCliente(clie);
				reporte.setRollo(roll);
				reporte.setKilo(totaal);
				reporte.setFecha(fechas);
				
				reportedosService.save(reporte);
				
				reporte = null;
			}
			}
			
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		} //llave de for
		
		System.out.println(produConsult.size());
		System.out.println(matriz.length);

		List<reporte> report = reporteService.findAll();
		
		model.addAttribute("titulo", "Elija Una opción Para su reporte");
		model.addAttribute("produConsult", report);
		model.addAttribute("cempleado", cempleado);
		model.addAttribute("cfactura", cfactura);
		model.addAttribute("cdibujo", cdibujo);
		model.addAttribute("cmaquina", cmaquina);

		return "produconsulta/reporte";
	}
}
