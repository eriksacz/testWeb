package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.appweb.models.entity.ClientLot;

public interface IClientLotDao extends CrudRepository<ClientLot, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE ClientLot set activo = 0  where id = ?1")
	void fetchByIdClientLot(Long id);
	
}
