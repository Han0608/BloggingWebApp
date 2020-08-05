package com.song.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.song.demo.domain.ArticleType;
import com.song.demo.repository.ArticleTypeRepository;

@Service
public class ArticleTypeService {
	
	@Autowired
	private ArticleTypeRepository articleTypeRepository;
	
	public ArticleType save(ArticleType articleType) {
		return articleTypeRepository.save(articleType);
		
	}

	public List<ArticleType> findAllType() {
		return articleTypeRepository.findAll();
	}

	public ArticleType findById(Integer typeId) {
		return  articleTypeRepository.findById(typeId).get();
	}
	
	public void updateType(ArticleType articleType) {
		  articleTypeRepository.save(articleType);
		
	}

	public void deleteByTypeId(Integer typeId) {
		articleTypeRepository.deleteByTypeId(typeId);
		
	}

	public void deleteById(Integer typeId) {
		articleTypeRepository.deleteById(typeId);
		
	}

	public List<ArticleType> findAllByUserId(Integer userId) {
		 return articleTypeRepository.findAllByUserUserId(userId);
	}

	public ArticleType findByTypeId(Integer typeId) {
		 
		return articleTypeRepository.findByTypeId(typeId);
	}

}
