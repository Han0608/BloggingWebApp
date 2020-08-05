package com.song.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.song.demo.dao.AdminDao;
import com.song.demo.domain.Admin;
import com.song.demo.domain.User;
import com.song.demo.repository.AdminRepository;
import com.song.demo.repository.UserRepository;
@Service

public class AdminService {
	@Autowired
	AdminDao adminDao;
	@Autowired
	private AdminRepository adminRepository;

	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}
	public Admin FindNameAndPsw(String adminName, String password) {
		return adminDao.findByAdminNameAndPassword(adminName, password);
	}
	@Transactional
	public Admin register(Admin admin) {
		return adminRepository.save(admin);
	}

	public List<Admin> findByName(String adminName) {
		return adminDao.findByAdminName(adminName);
	}
	/*
	public List<Admin> findAll(int page, int size){
		Sort sort = Sort.by(Direction.DESC,"adminId");
		Pageable pa = PageRequest.of(page, size, sort);
		
		return adminRepository.findAll(pa).getContent();
	}*/
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}
	public Admin findById(Integer adminId) {
		return adminRepository.findByAdminId(adminId).get();
	}

	
}
