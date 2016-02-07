package com.springbootapplication.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootapplication.model.User;
import com.springbootapplication.ws.repository.UserRepository;

@Service
public class UserDetailServiceBean implements UserDetailService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		
		if(user.getId() == null){
			return null;
		}
		
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

}
