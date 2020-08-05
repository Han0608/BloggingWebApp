package com.song.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.song.demo.dao.UserDao;
 
import com.song.demo.domain.User;
import com.song.demo.repository.UserRepository;
 
@Service
public class UserService {
 
	@Autowired
	UserDao userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	public User FindNameAndPsw(String userName, String password) {
		return userDao.findByUserNameAndPassword(userName, password);
	}
	
	@Transactional
	public User register(User user) {
		return userRepository.save(user);
	}

	
	public List<User> findByName(String userName) {
		return userDao.findByUserName(userName);
		
	}
 
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	
	 
	public Optional<User> findByUserId(Integer userId) {
		 
		return userRepository.findByUserId(userId);
	}
	
	public List<User> findByUserName(String userName){
		return userRepository.findByUserName(userName);
	}
	
	public User findById(Integer userId) {
		return userRepository.findByUserId(userId).get();
	}
 
	
    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }
	 
	public void update(User user) {
		userRepository.save(user);
		
	}

	
}
