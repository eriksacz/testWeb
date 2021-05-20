package com.test.appweb.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.test.appweb.models.entity.Dibujo;
import com.test.appweb.models.entity.EntradaFactura;
import com.test.appweb.models.entity.Maquina;
import com.test.appweb.models.entity.Produccion;

public interface IProduccionDao extends CrudRepository<Produccion, Long>{
	
	@Query("select p from Produccion p WHERE p.entradaFactura = ?1 and p.maquina = ?2 and p.dibujo = ?3")
	public List<Produccion> fetchByrollo(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo); 
	

	@Query("select p from Produccion p WHERE p.entradaFactura = ?1 and p.maquina = ?2 and p.dibujo = ?3 and p.fecha = ?4 ")	
	public List<Produccion> fetchByProduccion(EntradaFactura entradaFactura, Maquina maquina, Dibujo dibujo, Date fecha); 

	
}
