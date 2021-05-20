package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Composicion;

public interface IComposicionService {

	public List<Composicion> findAll();
	
	public void save(Composicion composicion);
	
	public Composicion findOne(Long id);
	
	public void fetchByIdComposicion(Long id);
}
