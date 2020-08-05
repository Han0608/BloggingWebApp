package com.song.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

 
@Entity(name = "t_admin")
public class Admin {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminId;
	
	@Column(length=60, nullable=false)
	@NotBlank(message="用户名不能为空！")
	private String adminName;
	
	@Column(length=50)
	private String password;
	
	@Column(length=50)

	
	
	private boolean enabled = true;
	

	public Admin() {
	}


	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public Admin(Integer adminId, @NotBlank(message = "用户名不能为空！") String adminName, String password, boolean enabled) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.password = password;
		this.enabled = enabled;
	}
}

