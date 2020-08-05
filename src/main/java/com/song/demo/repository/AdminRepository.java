package com.song.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.song.demo.domain.Admin;
import com.song.demo.domain.User;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin>  findByAdminId(Integer adminId);



}
