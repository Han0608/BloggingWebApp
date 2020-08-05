package com.song.demo.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

 
@Entity(name = "t_article")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Article {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer articleId;
	
	
	
	//@Column(length=50, nullable=false)
	//@NotBlank(message="文章标题不能为空！")
	private String title;  //文章标题
	

	//@Column(length=2000)
	private String content; //文章内容
	
	@CreatedDate
	private LocalDate publishDate; //文章发表日期
	
	@JoinColumn(name="author")
	@ManyToOne
	private User user;//作者
	
	
	
	@ManyToOne
	private ArticleType articleType; //所属文章类型
		
	 

	
	
	public Article() {
	}



	public Integer getArticleId() {
		return articleId;
	}



	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}



	 



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public LocalDate getPublishDate() {
		publishDate = LocalDate.now();
		return publishDate;
	}



	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}






	public Article(Integer articleId, ArticleType articleType, String title,
			String content, LocalDate publishDate) {
		super();
		this.articleId = articleId;
		this.articleType = articleType;
		this.title = title;
		this.content = content;
		this.publishDate = publishDate;
		//this.author = author;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public ArticleType getType() {
		return articleType;
	}



	public void setType(ArticleType type) {
		this.articleType = type;
	}



	public ArticleType getArticleType() {
		return articleType;
	}



	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	
	
}

