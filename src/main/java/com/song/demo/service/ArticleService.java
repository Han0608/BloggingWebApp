package com.song.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.song.demo.domain.Article;
import com.song.demo.domain.ArticleType;
import com.song.demo.domain.User;
import com.song.demo.repository.ArticleRepository;
import com.song.demo.repository.ArticleTypeRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	// 保存文章
	public Article save(Article article) {
		return articleRepository.save(article);
	}

	// 发表文章
	@Transactional
	public Article publishArticle(Article article) {
		return articleRepository.save(article);
	}

	public void deleteById(Integer articleId) {
		articleRepository.deleteById(articleId);

	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}
	public Page<Article> findAll(int page, int size) {
		Order order1 = new Order(Direction.DESC, "publishDate");
		Pageable pageable = PageRequest.of(page, size, 
			Sort.by(order1));
		Page<Article> objs = articleRepository.findAll(pageable);
		return objs;
		}
	public Article findById(Integer articleId) {

		return articleRepository.findById(articleId).get();
	}

	public void updateArticle(Article article) {

		articleRepository.save(article);
	}

	
	 public List<Article> findAllByUserId(Integer userId) { return
	  articleRepository.findAllByUserUserId(userId); }
	 

	
	 public List<Article> findByTitle(String title) { 
	// TODO Auto-generated method stub 
		 return articleRepository.findByTitleContaining(title);
		 }

	public List<Article> findByType(ArticleType type) {
		// TODO Auto-generated method stub
		return articleRepository.findByArticleType(type);
	}
	 

}
