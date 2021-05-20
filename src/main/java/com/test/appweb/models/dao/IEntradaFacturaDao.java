package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.test.appweb.models.entity.EntradaFactura;
public interface IEntradaFacturaDao extends JpaRepository<EntradaFactura, Long> {
	

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE EntradaFactura set activo = 0  where id = ?1")
	void fetchByIdentradaFactura(Long id);
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE EntradaFactura set estado = 0  where id = ?1")
	void fetchByIdentradaFacturados(Long id);
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE EntradaFactura set estado = 1  where id = ?1")
	void fetchByIdentradaFacturatres(Long id);

	
	


}
