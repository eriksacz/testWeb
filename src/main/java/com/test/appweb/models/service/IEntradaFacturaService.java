package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.EntradaFactura;

public interface IEntradaFacturaService {

	public List<EntradaFactura> findAll();
	
	public void save(EntradaFactura entradaFactura);
	
	public EntradaFactura findOne(Long id);
	
	public void fetchByIdentradaFactura(Long id);
	
	public void fetchByIdentradaFacturados(Long id);
	
	public void fetchByIdentradaFacturatres(Long id);
	
	
}
