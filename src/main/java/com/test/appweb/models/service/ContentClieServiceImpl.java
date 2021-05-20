package com.test.appweb.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.appweb.models.dao.IContentClientDao;
import com.test.appweb.models.entity.ContentClient;

@Service
public class ContentClieServiceImpl implements IContentClientService{

	@Autowired
	private IContentClientDao contentClientDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<ContentClient> findAll() {
		
		return (List<ContentClient>) contentClientDao.findAll();
	}

	@Transactional
	@Override
	public void save(ContentClient contentClient) {
		contentClientDao.save(contentClient);
	}

	@Transactional(readOnly = true)
	@Override
	public ContentClient findOne(Long id) {
		
		return contentClientDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void fetchByIdConcentClient(Long id) {
		contentClientDao.fetchByIdConcentClient(id);
		
	}

}
