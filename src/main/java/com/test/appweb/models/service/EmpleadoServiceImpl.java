package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.IEmpleadoDao;
import com.test.appweb.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao empleadoDao;

	@Transactional(readOnly = true)
	@Override
	public List<Empleado> findAll() {

		return (List<Empleado>) empleadoDao.findAll();
	}

	@Transactional
	@Override
	public void save(Empleado empleado) {

		empleadoDao.save(empleado);
	}

	@Transactional(readOnly = true)
	@Override
	public Empleado findOne(Long id) {

		return empleadoDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void delete(Long id) {

		empleadoDao.deleteById(id);

	}

	@Transactional(readOnly = true)
	@Override
	public Page<Empleado> findAll(Pageable pageable) {
		
		return empleadoDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void fetchByIdEmpleado(Long id) {
		
		empleadoDao.fetchByIdEmpleado(id);
	}


}
