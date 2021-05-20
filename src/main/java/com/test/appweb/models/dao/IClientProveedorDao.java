package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.appweb.models.entity.ClientProveedor;

public interface IClientProveedorDao extends CrudRepository<ClientProveedor, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE ClientProveedor set activo = 0  where id = ?1")
	void fetchByIdClientProveedor(Long id);
	
}
