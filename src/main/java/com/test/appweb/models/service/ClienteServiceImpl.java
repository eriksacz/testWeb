package com.test.appweb.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IClienteDao;
import com.test.appweb.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDao clienteDao;
	


	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
	
		clienteDao.save(cliente);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {
		
		return clienteDao.findById(id).orElse(null);
	}
	

	@Transactional
	@Override
	public void delete(Long id) {

		clienteDao.deleteById(id);

	}

	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> findAll(Pageable pageable) {

		return clienteDao.findAll(pageable);
	}



	@Override
	@Transactional
	public void fetchByIdCliente(Long id) {
		clienteDao.fetchByIdCliente(id);
	}

	@Override
	@Transactional
	public Cliente fetchByCliente(Long entradaFactu) {
		// TODO Auto-generated method stub
		return clienteDao.fetchByCliente(entradaFactu);
	}






}
