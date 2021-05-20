package com.test.appweb.models.service;

import java.util.List;
import com.test.appweb.models.entity.Maquina;

public interface IMaquinaService {
	
	
	public List<Maquina> findAll();
	
	public void save(Maquina maquina);
	
	public Maquina findOne(Long id);
	
	public void fetchByIdMaquina(Long id);
	
	public void fetchByIdMaquinados(Long id);
	
	public void fetchByIdMaquinatres(Long id);

}
