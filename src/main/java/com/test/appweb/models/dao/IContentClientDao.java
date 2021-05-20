package com.test.appweb.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.test.appweb.models.entity.ContentClient;

public interface IContentClientDao extends JpaRepository<ContentClient, Long>{

	
	@Modifying(clearAutomatically = true) 
	@Query("UPDATE ContentClient set activo = 0  where id = ?1")
	void fetchByIdConcentClient(Long id);
	
}
