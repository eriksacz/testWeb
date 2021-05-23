package com.test.appweb.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.test.appweb.models.entity.ProduConsulta;
public interface IProduConsultaDao extends JpaRepository<ProduConsulta, Long>{
	

	@Query(value = "select * from produccionres  WHERE fechar Between ?1 and ?2",nativeQuery = true)
	public List<ProduConsulta> fetchByIntervalo(String fechainicio, String fechafin); 
	
	@Query(value = "select * from produccionmineria  WHERE empleador_id = ?1 and entrada_facturar_id = ?2 and dibujor_id = ?3 and maquinar_id = ?4 and turnor_id = ?5",nativeQuery = true)
	public List<String> fetchByReporte(Long long1, Long long2, Long long3, Long long4, Long long5); 

}
