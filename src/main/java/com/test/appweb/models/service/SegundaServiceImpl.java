package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.appweb.models.dao.ISegundaDao;
import com.test.appweb.models.entity.Segunda;

@Service
public class SegundaServiceImpl implements ISegundaService{
	
	@Autowired
	private ISegundaDao segundaDao;

	@Override
	public List<Segunda> findAll() {
		return (List<Segunda>) segundaDao.findAll();
	}

	@Override
	public void save(Segunda segunda) {
		segundaDao.save(segunda);
	}

	@Override
	public Segunda findOne(long id) {
		return segundaDao.findById(id).orElse(null);
	}

}
