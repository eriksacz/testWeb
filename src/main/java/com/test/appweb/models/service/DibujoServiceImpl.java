package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.appweb.models.dao.IDibujoDao;
import com.test.appweb.models.entity.Dibujo;

@Service
public class DibujoServiceImpl implements IDibujoService {

	@Autowired
	private IDibujoDao dibujoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Dibujo> findAll() {
		
		return (List<Dibujo>) dibujoDao.findAll();
	}

	@Transactional
	@Override
	public void save(Dibujo dibujo) {
		dibujoDao.save(dibujo);
		
	}

	@Transactional(readOnly = true)
	@Override
	public Dibujo findOne(Long id) {
		
		return dibujoDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdDibujo(Long id) {
		dibujoDao.fetchByIdDibujo(id);
	}

}
