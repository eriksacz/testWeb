package com.test.appweb.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IProduccionDao;
import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.entity.Produccion;

@Service
public class ProduccionServiceImpl implements IProduccionService {

	@Autowired
	private IProduccionDao produccionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Produccion> findAll() {
		return (List<Produccion>) produccionDao.findAll();
	}

	@Transactional
	@Override
	public void save(Produccion produccion) {
		produccionDao.save(produccion);
	}

	@Transactional(readOnly = true)
	@Override
	public Produccion findOne(Long id) {
		return produccionDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public List<Produccion> fetchByrollo(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo) {
		
		return (List<Produccion>) produccionDao.fetchByrollo(entradaFactura, maquina, dibujo);
	}

	@Transactional
	@Override
	public List<Produccion> fetchByProduccion(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo,
			Date fecha) {
		
		return (List<Produccion>) produccionDao.fetchByProduccion(entradaFactura, maquina, dibujo, fecha);
	}

	@Transactional
	@Override
	public void delete(Long id) {
	produccionDao.deleteById(id);
		
	}




}
