package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.Composicion;

public interface IComposicionDao extends JpaRepository<Composicion, Long>{
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Composicion set activo = 0  where id = ?1")
	void fetchByIdComposicion(Long id);

}
