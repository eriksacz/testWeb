package com.test.appweb.models.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IDibuFacDao;
import com.test.appweb.models.entity.DibuFac;

@Service
public class DibuFacServiceImpl implements IDibujoFacService {

	@Autowired
	private IDibuFacDao dibuFacDao;

	@Transactional
	@Override
	public void save(DibuFac dibuFac) {
		dibuFacDao.save(dibuFac);
	}

	@Transactional
	@Override
	public void fetchByIdDibuFac(Long id) {
		dibuFacDao.fetchByIdDibuFac(id);
	}

	@Transactional(readOnly = true)
	@Override
	public DibuFac findOne(Long id) {
		return dibuFacDao.findById(id).orElse(null);
	}
	


}
