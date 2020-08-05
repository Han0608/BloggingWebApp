package com.song.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.song.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	Optional<User>  findByUserId(Integer userId);

	List<User> findByUserName(String userName);

	//User updateById(Integer userId);


}
