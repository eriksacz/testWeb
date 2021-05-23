package com.test.appweb.models.service;

import java.util.List;
import com.test.appweb.models.entity.ProduConsulta;

public interface IProduConsultaService {
	
	public List<ProduConsulta> findAll();
	
	public ProduConsulta findOne(Long id);
	
	public List<ProduConsulta> fetchByIntervalo(String fechainicio, String fechafin); 

	public List<String> fetchByReporte(Long long1, Long long2, Long long3, Long long4, Long long5); 
}
