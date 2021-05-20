package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IClientProveedorDao;
import com.test.appweb.models.entity.ClientProveedor;

@Service
public class ClientProveedorServiceImpl implements IClientProveedorService {

	@Autowired
	private IClientProveedorDao clientProveedorDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientProveedor> findAll() {
		// TODO Auto-generated method stub
		return (List<ClientProveedor>) clientProveedorDao.findAll();
	}

	@Override
	@Transactional
	public void save(ClientProveedor clientProveedor) {
		clientProveedorDao.save(clientProveedor);
	}

	@Override
	@Transactional(readOnly = true)
	public ClientProveedor findOne(Long id) {
		// TODO Auto-generated method stub
		return clientProveedorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void fetchByIdClientProveedor(Long id) {
		clientProveedorDao.fetchByIdClientProveedor(id);
	}

}
