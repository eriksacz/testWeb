package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.ISalidaDao;
import com.test.appweb.models.entity.Salida;

@Service
public class SalidaServiceImpl implements ISalidaService {

	@Autowired
	private ISalidaDao salidaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Salida> findAll() {
		
		return (List<Salida>) salidaDao.findAll();
	}

	@Transactional
	@Override
	public void save(Salida salida) {
		salidaDao.save(salida);
	}

	@Transactional(readOnly = true)
	@Override
	public Salida findOne(long id) {
		
		return salidaDao.findById(id).orElse(null);
	}

}
