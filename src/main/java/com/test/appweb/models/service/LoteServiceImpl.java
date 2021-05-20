package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.ILoteDao;
import com.test.appweb.models.entity.Lote;

@Service
public class LoteServiceImpl implements ILoteService {

	@Autowired
	private ILoteDao loteDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Lote> findAll() {
		
		return (List<Lote>) loteDao.findAll();
	}

	@Transactional
	@Override
	public void save(Lote lote) {
		loteDao.save(lote);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Lote findOne(Long id) {
		
		return loteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdLote(Long id) {
		loteDao.fetchByIdLote(id);
		
	}

}
