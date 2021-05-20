package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.Lote;

public interface ILoteDao extends JpaRepository<Lote, Long> {

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Lote set activo = 0  where id = ?1")
	void fetchByIdLote(Long id);
	
}
