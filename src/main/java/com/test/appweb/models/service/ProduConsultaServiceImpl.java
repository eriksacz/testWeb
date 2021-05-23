package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.appweb.models.dao.IProduConsultaDao;
import com.test.appweb.models.entity.ProduConsulta;

@Service
public class ProduConsultaServiceImpl implements IProduConsultaService {

	@Autowired
	private IProduConsultaDao produConsultaDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<ProduConsulta> findAll() {
		return (List<ProduConsulta>) produConsultaDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public ProduConsulta findOne(Long id) {
		return produConsultaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public List<ProduConsulta> fetchByIntervalo(String fechainicio, String fechafin) {
	
		return produConsultaDao.fetchByIntervalo(fechainicio, fechafin);
	}

	@Override
	public List<String> fetchByReporte(Long long1, Long long2, Long long3, Long long4, Long long5) {
		// TODO Auto-generated method stub
		return produConsultaDao.fetchByReporte(long1, long2, long3, long4, long5);
	}



	
	
}
