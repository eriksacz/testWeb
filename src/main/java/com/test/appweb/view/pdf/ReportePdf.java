package com.test.appweb.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.test.appweb.models.entity.reporte;

@Component("produconsulta/reporte")
public class ReportePdf extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<reporte> report = (List<reporte>) model.get("produConsult");
		
		
		PdfPTable table = new PdfPTable(12);
		
		report.forEach(repor -> {
			table.addCell(repor.getEmpleado().getNombre());
			table.addCell(repor.getDibujo().getNomdibujo());
			table.addCell(repor.getProveedor().getNomproveedor());
			table.addCell(repor.getComposicion().getNomcomposicion());
			table.addCell(repor.getMaquina().getNumero());
			table.addCell(repor.getLote().getNomlote());
			table.addCell(repor.getTurno().getTurno());
			table.addCell(repor.getEntradaFactura().getNoFactura());
			table.addCell(repor.getCliente().getCliente());
			table.addCell(repor.getRollor());
			table.addCell(repor.getKilor());
			table.addCell(repor.getFechar());			
		});
		
		document.add(table);
		
	}

}
