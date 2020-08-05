package com.song.demo.domain;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
 
@Entity(name = "t_user")
public class User {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(length=60, nullable=false)
	@NotBlank(message="用户名不能为空！")
	private String userName;
	
	@Column(length=50)
	private String password;
 
	@Column(length=2)
	private String sex;
	
	private LocalDate birthday;
	
	private Integer age;
	
	private Integer ban=0;
	private boolean enabled = true;
	

	public User() {
	}
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public Integer getAge() {
		if (birthday == null)
			return null;
		
		age = LocalDate.now().getYear() - birthday.getYear();
		
		return age;
	}
	
	public void setAge(Integer age) {
		
		this.age = age;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public User(Integer userId, String userName, String password, String sex, LocalDate birthday, Integer age,
			boolean enabled) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.age = age;
		this.enabled = enabled;
 
	}
	public Integer getBan() {
		return ban;
	}
	public void setBan(Integer ban) {
		this.ban = ban;
	}
	
	
}

