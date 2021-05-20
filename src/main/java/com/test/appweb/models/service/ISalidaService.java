package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Salida;

public interface ISalidaService {

	public List<Salida> findAll();
	
	public void save(Salida salida);
	
	public Salida findOne(long id);
	
}
