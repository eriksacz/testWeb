package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.FacDibuMaqui;

public interface IFacDibuMaquiService {

	public List<FacDibuMaqui> finAll();
	
	public void save(FacDibuMaqui facDibuMaqui);
	
	public FacDibuMaqui findOne(Long id);
	
	public void fetchByIdFacDibuMaqui(Long id);
	
}
