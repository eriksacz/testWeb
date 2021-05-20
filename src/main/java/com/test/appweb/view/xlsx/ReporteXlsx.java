package com.test.appweb.view.xlsx;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.test.appweb.models.entity.reporte;

@Component("reportes.xlsx")
public class ReporteXlsx extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"mineria_view.xlsx\"");
		
		@SuppressWarnings("unchecked")
		List<reporte> repo = (List<reporte>) model.get("reportess");
		
		Sheet sheet = workbook.createSheet();
		
		 AtomicInteger count=new AtomicInteger(0);
		
		repo.forEach(report -> {
			Row fila = sheet.createRow(count.incrementAndGet());
			fila.createCell(0).setCellValue(report.getEmpleado().getNombre());
			fila.createCell(1).setCellValue(report.getDibujo().getNomdibujo());
			fila.createCell(2).setCellValue(report.getProveedor().getNomproveedor());
			fila.createCell(3).setCellValue(report.getComposicion().getNomcomposicion());
			fila.createCell(4).setCellValue(report.getMaquina().getNumero());
			fila.createCell(5).setCellValue(report.getLote().getNomlote());
			fila.createCell(6).setCellValue(report.getTurno().getTurno());
			fila.createCell(7).setCellValue(report.getEntradaFactura().getNoFactura());
			fila.createCell(8).setCellValue(report.getCliente().getCliente());
			fila.createCell(9).setCellValue(report.getRollor());
			fila.createCell(10).setCellValue(report.getKilor());
			fila.createCell(11).setCellValue(report.getFechar());	
		});
		
		
	}

}
