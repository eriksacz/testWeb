package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IreporteDao;
import com.test.appweb.models.entity.reporte;

@Service
public class reporteServiceImpl implements IreporteService {

	@Autowired
	private IreporteDao reporteDao;

	@Transactional(readOnly = true)
	@Override
	public List<reporte> findAll() {
		
		return (List<reporte>) reporteDao.findAll();
	}

	@Transactional
	@Override
	public void deleteMio() {
		reporteDao.deleteMio();
	}



	
	

}
