package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Lote;

public interface ILoteService {

	public List<Lote> findAll();
	
	public void save(Lote lote);
	
	public Lote findOne(Long id);
	
	public void fetchByIdLote(Long id);
}
