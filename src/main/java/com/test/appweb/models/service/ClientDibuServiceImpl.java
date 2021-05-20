package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IClientDibuDao;
import com.test.appweb.models.entity.ClientDibu;

@Service
public class ClientDibuServiceImpl implements IClientDibuService {

	@Autowired
	private IClientDibuDao clientDibuDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientDibu> findAll() {
		// TODO Auto-generated method stub
		return (List<ClientDibu>) clientDibuDao.findAll();
	}

	@Override
	@Transactional
	public void save(ClientDibu clientDibu) {
		clientDibuDao.save(clientDibu);

	}

	@Override
	@Transactional(readOnly = true)
	public ClientDibu findOne(Long id) {
		// TODO Auto-generated method stub
		return clientDibuDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void fetchByIdClientDibu(Long id) {
		clientDibuDao.fetchByIdClientDibu(id);
	}

}
