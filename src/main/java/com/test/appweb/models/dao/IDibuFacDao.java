package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.DibuFac;

public interface IDibuFacDao extends JpaRepository<DibuFac, Long>{
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE DibuFac set activo = 0  where id = ?1")
	void fetchByIdDibuFac(Long id);

}
