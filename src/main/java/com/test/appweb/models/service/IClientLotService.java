package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.ClientLot;

public interface IClientLotService {
	
	public List<ClientLot> findall();
	
	public void save(ClientLot clientLot);
	
	public ClientLot findOne(Long id);
	
	public void fetchByIdClientLot(Long id);
	

}
