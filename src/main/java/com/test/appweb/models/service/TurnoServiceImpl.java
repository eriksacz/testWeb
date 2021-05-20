package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.ITurnoDao;
import com.test.appweb.models.entity.Turno;

@Service
public class TurnoServiceImpl implements ITurnoService {

	@Autowired
	private ITurnoDao turnoDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Turno> findAll() {
		return (List<Turno>) turnoDao.findAll();
	}

}
