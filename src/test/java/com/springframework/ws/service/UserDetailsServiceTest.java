package com.springframework.ws.service;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.model.User;
import com.springbootapplication.service.UserDetailService;
import com.springframework.AbstractTest;

@Transactional
public class UserDetailsServiceTest extends AbstractTest {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Before
	public void setUp(){
		userDetailService.evictCache();
	}
	
	@Test
	public void testGetAllUsers(){
		
		Collection<User> userList = userDetailService.findAll();
		
		Assert.assertNotNull("FAILED: userlist empty", userList);
		
	}
	
	@Test
	public void testcreateUser(){
		User user = new User();
		user.setName("Tom");
		user.setProfession("Tech");
		
		User newUser = userDetailService.create(user);
		
		Assert.assertNotNull("FAILED: user not created", newUser);
		Assert.assertEquals("FAILED: user details don't match (Name)", "Tom", newUser.getName());
		Assert.assertEquals("FAILED: user details don't match (Profession)", "Tech", newUser.getProfession());
		
		Collection<User> userList = userDetailService.findAll();
//		
//		Assert.assertEquals("FAILED - user not created size don't match", 3, userList);
		
		
	}
	
	@Test
	public void testUpdateUser(){
		
		Long id = new Long(1);
		
		User user = userDetailService.findOne(id);
		
		Assert.assertEquals("FAILED: user details don't match", "Aditya Srivastava", user.getName());
		Assert.assertEquals("FAILED: user details don't match", "Computer Science", user.getProfession());
		
		user.setName("Test Name");

		User updatedUser = userDetailService.update(user);
		
		Assert.assertNotNull("FAILED: user details not updated", updatedUser);
		Assert.assertEquals("FAILED: user details don't match", "Test Name", updatedUser.getName());
		
		
		
	}
	
	@Test
	public void testDeleteUser(){
		
		Long id = new Long(1);
		
		User user = userDetailService.findOne(id);
		
//		Assert.assertEquals("FAILED: user details don't match", "Aditya Srivastava", user.getName());
//		Assert.assertEquals("FAILED: user details don't match", "Computer Science", user.getProfession());
		
		userDetailService.delete(user.getId());
		
		
		Collection<User> userList = userDetailService.findAll();
		
//		Assert.assertEquals("FAILED - user not created size don't match", 1, userList);
		
		
		
	}

}
