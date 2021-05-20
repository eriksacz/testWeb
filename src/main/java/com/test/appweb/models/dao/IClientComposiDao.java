package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.appweb.models.entity.ClientComposi;

public interface IClientComposiDao extends CrudRepository<ClientComposi, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE ClientComposi set activo = 0  where id = ?1")
	void fetchByIdClientComposi(Long id);
	
}
