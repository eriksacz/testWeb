package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IClientLotDao;
import com.test.appweb.models.entity.ClientLot;

@Service
public class ClientLotServiceImpl implements IClientLotService{

	@Autowired
	private IClientLotDao clientLotDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClientLot> findall() {
		// TODO Auto-generated method stub
		return (List<ClientLot>) clientLotDao.findAll();
	}

	@Override
	@Transactional
	public void save(ClientLot clientLot) {
		clientLotDao.save(clientLot);
		
	}

	@Override
	@Transactional(readOnly = true)
	public ClientLot findOne(Long id) {
		// TODO Auto-generated method stub
		return clientLotDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdClientLot(Long id) {
		clientLotDao.fetchByIdClientLot(id);	
	}

}
