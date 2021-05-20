package com.test.appweb.models.service;

import com.test.appweb.models.entity.DibuFac;

public interface IDibujoFacService {
	
	public void save(DibuFac dibuFac);
	
	public void fetchByIdDibuFac(Long id);
	
	public DibuFac findOne(Long id);
	


}
