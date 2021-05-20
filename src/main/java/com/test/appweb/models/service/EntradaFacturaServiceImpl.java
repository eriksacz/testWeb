package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IEntradaFacturaDao;
import com.test.appweb.models.entity.EntradaFactura;

@Service
public class EntradaFacturaServiceImpl implements IEntradaFacturaService {

	@Autowired
	private IEntradaFacturaDao entradaFacturaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<EntradaFactura> findAll() {

		return (List<EntradaFactura>) entradaFacturaDao.findAll();
	}

	@Transactional
	@Override
	public void save(EntradaFactura entradaFactura) {
		entradaFacturaDao.save(entradaFactura);
	}

	@Transactional(readOnly = true)
	@Override
	public EntradaFactura findOne(Long id) {
	
		return entradaFacturaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdentradaFactura(Long id) {
		entradaFacturaDao.fetchByIdentradaFactura(id);
		
	}

	@Transactional
	@Override
	public void fetchByIdentradaFacturados(Long id) {
		entradaFacturaDao.fetchByIdentradaFacturados(id);
		
	}

	@Transactional
	@Override
	public void fetchByIdentradaFacturatres(Long id) {
		entradaFacturaDao.fetchByIdentradaFacturatres(id);
		
	}


}
