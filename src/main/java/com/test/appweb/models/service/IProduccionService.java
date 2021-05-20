package com.test.appweb.models.service;

import java.util.Date;
import java.util.List;

import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.entity.Produccion;

public interface IProduccionService {
	
	public List<Produccion> findAll();
	
	public void save(Produccion produccion);
	
	public Produccion findOne(Long id);
	
	public void delete(Long id);
	
	public List<Produccion> fetchByrollo(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo); 
	

	public List<Produccion> fetchByProduccion(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo, Date fecha); 
	
}
