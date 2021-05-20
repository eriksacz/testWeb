package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Dibujo;

public interface IDibujoService {
	
	public List<Dibujo> findAll();
	
	public void save(Dibujo dibujo);
	
	public Dibujo findOne(Long id);
	
	public void fetchByIdDibujo(Long id);

}
