package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.MaquiDibu;

public interface IMaquiDibuDao extends JpaRepository<MaquiDibu, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE MaquiDibu set activo = 0  where id = ?1")
	void fetchByIdMaquiDibu(Long id);
	
	
}
