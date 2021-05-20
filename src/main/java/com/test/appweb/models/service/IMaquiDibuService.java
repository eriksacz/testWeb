package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.MaquiDibu;

public interface IMaquiDibuService {
	
	public List<MaquiDibu> findAll();
	
	public void save(MaquiDibu maquiDibu);
	
	public MaquiDibu findOne(Long id);
	
	public void fetchByIdMaquiDibu(Long id);
	


}
