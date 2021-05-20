package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.FacDibuMaqui;

public interface IFacDibuMaquiDao extends JpaRepository<FacDibuMaqui, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE FacDibuMaqui set activo = 0  where id = ?1")
	void fetchByIdFacDibuMaqui(Long id);
	
}
