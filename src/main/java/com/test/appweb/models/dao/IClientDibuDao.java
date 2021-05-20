package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.appweb.models.entity.ClientDibu;

public interface IClientDibuDao extends CrudRepository<ClientDibu, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE ClientDibu set activo = 0  where id = ?1")
	void fetchByIdClientDibu(Long id);
	
}
