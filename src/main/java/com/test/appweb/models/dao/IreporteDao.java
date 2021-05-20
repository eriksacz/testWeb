package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.test.appweb.models.entity.reporte;

public interface IreporteDao extends CrudRepository<reporte, Long>{

	@Modifying(clearAutomatically = true)
	@Query(value = "DELETE FROM reporte",nativeQuery = true)
	public void deleteMio(); 
	
}
