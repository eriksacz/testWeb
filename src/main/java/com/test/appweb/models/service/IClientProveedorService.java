package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.ClientProveedor;

public interface IClientProveedorService {

	public List<ClientProveedor> findAll();
	
	public void save(ClientProveedor clientProveedor);
	
	public ClientProveedor findOne(Long id);
	
	public void fetchByIdClientProveedor(Long id);
	
}
