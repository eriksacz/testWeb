package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.appweb.models.dao.IMaquiDibuDao;
import com.test.appweb.models.entity.MaquiDibu;

@Service
public class MaquiDibuServiceImpl implements IMaquiDibuService{

	@Autowired
	private IMaquiDibuDao maquiDibuDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<MaquiDibu> findAll() {
		
		return (List<MaquiDibu>) maquiDibuDao.findAll();
	}

	@Transactional
	@Override
	public void save(MaquiDibu maquiDibu) {
		maquiDibuDao.save(maquiDibu);
		
	}

	@Transactional(readOnly = true)
	@Override
	public MaquiDibu findOne(Long id) {
		
		return maquiDibuDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdMaquiDibu(Long id) {
		maquiDibuDao.fetchByIdMaquiDibu(id);
	}



}
