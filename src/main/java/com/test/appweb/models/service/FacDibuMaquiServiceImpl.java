package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IFacDibuMaquiDao;
import com.test.appweb.models.entity.FacDibuMaqui;

@Service
public class FacDibuMaquiServiceImpl implements IFacDibuMaquiService {

	@Autowired
	private IFacDibuMaquiDao facDibuMaquiDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<FacDibuMaqui> finAll() {
		
		return (List<FacDibuMaqui>) facDibuMaquiDao.findAll();
	}

	@Transactional
	@Override
	public void save(FacDibuMaqui facDibuMaqui) {
		facDibuMaquiDao.save(facDibuMaqui);

	}

	@Transactional(readOnly = true)
	@Override
	public FacDibuMaqui findOne(Long id) {
	
		return facDibuMaquiDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdFacDibuMaqui(Long id) {
	facDibuMaquiDao.fetchByIdFacDibuMaqui(id);
		
	}

}
