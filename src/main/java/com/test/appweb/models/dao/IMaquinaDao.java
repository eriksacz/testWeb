package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.Maquina;

public interface IMaquinaDao extends JpaRepository<Maquina, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Maquina set activo = 0  where id = ?1")
	void fetchByIdMaquina(Long id);
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Maquina set estado = 0  where id = ?1")
	void fetchByIdMaquinados(Long id);
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Maquina set estado = 1  where id = ?1")
	void fetchByIdMaquinatres(Long id);
	
	
}
