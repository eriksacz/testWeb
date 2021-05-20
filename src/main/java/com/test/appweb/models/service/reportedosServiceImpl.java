package com.test.appweb.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IreporteDao;
import com.test.appweb.models.dao.IreportedosDao;
import com.test.appweb.models.entity.reporte;
import com.test.appweb.models.entity.reportedos;

@Service
public class reportedosServiceImpl implements IreportedosService {

	@Autowired
	private IreportedosDao reportedosDao;
	
	@Transactional
	@Override
	public void save(reportedos reportedos) {
		
   reportedosDao.save(reportedos);
	}

}
