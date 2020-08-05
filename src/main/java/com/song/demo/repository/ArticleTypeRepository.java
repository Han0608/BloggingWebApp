package com.song.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.song.demo.domain.Admin;
import com.song.demo.domain.ArticleType;


public interface ArticleTypeRepository extends JpaRepository<ArticleType, Integer> {

	void deleteByTypeId(Integer typeId);

	List<ArticleType> findAllByUserUserId(Integer userId);

	ArticleType findByTypeId(Integer typeId);




}
