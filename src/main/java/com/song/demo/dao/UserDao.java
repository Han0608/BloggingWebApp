package com.song.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.song.demo.domain.User;
 
@Repository
public interface UserDao extends JpaRepository<User, Long> {
 
	public User findByUserNameAndPassword(String userName, String password);
 
	public List<User> findByUserName(String userName);
	
	public User findByUserId(Integer userId);
 
}
