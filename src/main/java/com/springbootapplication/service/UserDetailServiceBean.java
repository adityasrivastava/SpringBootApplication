package com.springbootapplication.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.model.User;
import com.springbootapplication.ws.repository.UserRepository;

@Service
@Transactional(
		propagation = Propagation.SUPPORTS, readOnly=true)
public class UserDetailServiceBean implements UserDetailService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED, readOnly=false)
	public User create(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED, readOnly=false)
	public User update(User user) {
		
		if(user.getId() == null){
			return null;
		}
		
		return userRepository.save(user);
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED, readOnly=false)
	public void delete(Long id) {
		userRepository.delete(id);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

}
