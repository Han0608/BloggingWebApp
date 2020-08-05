package com.song.demo.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//加一个department的外键



@Entity
public class ArticleType {
	@Id
	@GeneratedValue
	private Integer typeId;//主键，文章类型ID
		
	@Column(length=50)
	private String typeName ; //文章类型
	 
	 
	//private Integer typeAuthor;//创建人

	 
	@ManyToOne
	private User user;
	
	public Integer getTypeId() {
		return typeId;
	}


	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public ArticleType() {
		
	}

	public ArticleType(Integer typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	//	this.typeAuthor = typeAuthor;
		//this.user = user;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	

	
	
	
	


}

//定义关联关系