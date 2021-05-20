package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IComposicionDao;
import com.test.appweb.models.entity.Composicion;

@Service
public class ComposicionServiceImpl implements IComposicionService {

	@Autowired
	private IComposicionDao composicionDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Composicion> findAll() {
		
		return (List<Composicion>) composicionDao.findAll();
	}

	@Transactional
	@Override
	public void save(Composicion composicion) {
		composicionDao.save(composicion);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Composicion findOne(Long id) {
		
		return composicionDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdComposicion(Long id) {
		composicionDao.fetchByIdComposicion(id);
		
	}

}
