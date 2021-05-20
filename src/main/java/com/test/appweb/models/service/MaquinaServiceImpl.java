package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IMaquinaDao;
import com.test.appweb.models.entity.Maquina;

@Service
public class MaquinaServiceImpl implements IMaquinaService {

	@Autowired
	private IMaquinaDao maquinaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Maquina> findAll() {
		
		return (List<Maquina>) maquinaDao.findAll();
	}

	@Transactional
	@Override
	public void save(Maquina maquina) {
	maquinaDao.save(maquina);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Maquina findOne(Long id) {
		
		return maquinaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdMaquina(Long id) {
		maquinaDao.fetchByIdMaquina(id);
	}

	@Transactional
	@Override
	public void fetchByIdMaquinados(Long id) {
	maquinaDao.fetchByIdMaquinados(id);
		
	}

	@Transactional
	@Override
	public void fetchByIdMaquinatres(Long id) {
	maquinaDao.fetchByIdMaquinatres(id);
		
	}

	

}
