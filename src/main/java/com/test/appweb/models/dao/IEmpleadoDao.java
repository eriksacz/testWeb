package com.test.appweb.models.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.test.appweb.models.entity.Empleado;



public interface IEmpleadoDao extends PagingAndSortingRepository<Empleado, Long> {
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Empleado set activo = 0  where id = ?1")
	void fetchByIdEmpleado(Long id);
	
	
}
