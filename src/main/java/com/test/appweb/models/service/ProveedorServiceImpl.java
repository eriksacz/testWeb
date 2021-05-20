package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IProveedorDao;
import com.test.appweb.models.entity.Proveedor;

@Service
public class ProveedorServiceImpl implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Override
	public List<Proveedor> findAll() {
		
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Transactional
	@Override
	public void save(Proveedor proveedor) {
		proveedorDao.save(proveedor);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Proveedor findOne(Long id) {
	
		return proveedorDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdProveedor(Long id) {
		proveedorDao.fetchByIdProveedor(id);
		
	}

}
