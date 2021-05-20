package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Proveedor;

public interface IProveedorService {

	public List<Proveedor> findAll();
	
	public void save(Proveedor proveedor);
	
	public Proveedor findOne(Long id);
	
	public void fetchByIdProveedor(Long id);
	
}
