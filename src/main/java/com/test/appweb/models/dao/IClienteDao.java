package com.test.appweb.models.dao;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.test.appweb.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{
	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE Cliente set activo = 0  where id = ?1")
	void fetchByIdCliente(Long id);

	@Query("select p from Cliente p WHERE p.EntradaFactura = ?1")
	public Cliente fetchByCliente(Long entradaFactu); 
	
}
