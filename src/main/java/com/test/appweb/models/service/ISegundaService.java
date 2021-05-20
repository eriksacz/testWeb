package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Segunda;

public interface ISegundaService {
	
	public List<Segunda> findAll();
	
	public void save(Segunda segunda);
	
	public Segunda findOne(long id);

}
