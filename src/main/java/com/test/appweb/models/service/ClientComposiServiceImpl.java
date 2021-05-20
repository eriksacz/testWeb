package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IClientComposiDao;
import com.test.appweb.models.entity.ClientComposi;

@Service
public class ClientComposiServiceImpl implements IClientComposiService {

	@Autowired
	private IClientComposiDao clientComposiDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientComposi> findAll() {
		// TODO Auto-generated method stub
		return (List<ClientComposi>) clientComposiDao.findAll();
	}

	@Override
	@Transactional
	public void save(ClientComposi clientComposi) {
		clientComposiDao.save(clientComposi);

	}

	@Override
	@Transactional(readOnly = true)
	public ClientComposi findOne(Long id) {
		// TODO Auto-generated method stub
		return clientComposiDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void fetchByIdClientComposi(Long id) {
		clientComposiDao.fetchByIdClientComposi(id);
	}

}
