package com.song.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.song.demo.domain.Admin;
import com.song.demo.domain.User;
 
@Repository
public interface AdminDao extends JpaRepository<Admin, Long> {
 
	public Admin findByAdminNameAndPassword(String adminName, String password);
 
	public List<Admin> findByAdminName(String adminName);
 
}
