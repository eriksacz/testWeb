package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.ContentClient;

public interface IContentClientService {
	
	public List<ContentClient> findAll();
	
	public void save(ContentClient contentClient);
	
	public ContentClient findOne(Long id);
	
	public void fetchByIdConcentClient(Long id);

}
