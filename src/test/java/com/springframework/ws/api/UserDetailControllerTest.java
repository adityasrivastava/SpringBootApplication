package com.springframework.ws.api;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.service.UserDetailService;
import com.springframework.ws.service.AbstractControllerTest;


@Transactional
public class UserDetailControllerTest extends AbstractControllerTest {
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Before
	public void setup(){
		super.setUp();
	}
	
	@Test
	public void testGetUserDetails() throws Exception{
		
		String uri = "/api/getUsers";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("FAILED: status not 200 OK!!", 200, status);
		
		Assert.assertTrue("FAILED: Not expected Http Response", content.trim().length() > 0);
		
		
		
	}
	
	

}
