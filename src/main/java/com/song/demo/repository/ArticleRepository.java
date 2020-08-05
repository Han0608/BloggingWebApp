package com.song.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.song.demo.domain.Admin;
import com.song.demo.domain.Article;
import com.song.demo.domain.ArticleType;
import com.song.demo.domain.User;



public interface ArticleRepository extends JpaRepository<Article, Integer> {
	List<Article> findAllByUserUserId(Integer userId);
	 
	List<Article> findByTitleContaining(String title);

	Page<Article> findAll(Pageable pageable);

	List<Article> findByArticleType(ArticleType articleType);

	
	




}
