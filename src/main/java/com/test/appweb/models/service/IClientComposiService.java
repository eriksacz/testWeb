package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.ClientComposi;

public interface IClientComposiService {

	public List<ClientComposi> findAll();
	
	public void save(ClientComposi clientComposi);
	
	public ClientComposi findOne(Long id);
	
	public void fetchByIdClientComposi(Long id);
	
}
