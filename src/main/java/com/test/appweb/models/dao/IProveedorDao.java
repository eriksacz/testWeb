package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.Proveedor;

public interface IProveedorDao extends JpaRepository<Proveedor, Long>{

	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Proveedor set activo = 0  where id = ?1")
	void fetchByIdProveedor(Long id);
	
}
