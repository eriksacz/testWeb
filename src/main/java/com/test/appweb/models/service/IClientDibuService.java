package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.ClientDibu;

public interface IClientDibuService {
	
	public List<ClientDibu> findAll();

	public void save(ClientDibu clientDibu);
	
	public ClientDibu findOne(Long id);
	
	public void fetchByIdClientDibu(Long id);
	
}
