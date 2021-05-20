package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.Dibujo;

public interface IDibujoDao extends JpaRepository<Dibujo, Long> {

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Dibujo set activo = 0  where id = ?1")
	void fetchByIdDibujo(Long id);

	
}
