package com.springbootapplication.service;

import java.util.Collection;

import com.springbootapplication.model.User;

public interface UserDetailService {
	
	Collection<User> findAll();
	User findOne(Long id);
	User create(User user);
	User update(User user);
	void delete(Long id);
	void evictCache();
	

}
