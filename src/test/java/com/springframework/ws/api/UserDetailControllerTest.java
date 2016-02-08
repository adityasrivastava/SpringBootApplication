package com.springframework.ws.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.springbootapplication.model.User;
import com.springbootapplication.service.UserDetailService;
import com.springframework.ws.service.AbstractControllerTest;

@Transactional
public class UserDetailControllerTest extends AbstractControllerTest {

	@Autowired
	private UserDetailService userDetailService;

	@Before
	public void setup() {
		super.setUp();
	}

	@Test
	public void testGetUserDetails() throws Exception {

		String uri = "/api/getUsers";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();

		int status = result.getResponse().getStatus();

		Assert.assertEquals("FAILED: status not 200 OK!!", 200, status);

		Assert.assertTrue("FAILED: Not expected Http Response", content.trim().length() > 0);

	}

	@Test
	public void testUpdateUserDetails() throws Exception {

		String uri = "/api/updateUser";
		
		User updateUser = new User();
		updateUser.setId(new Long(1));
		updateUser.setName("TestUpdateName");
		updateUser.setProfession("TestUpdateProfession");
		
		String inputJsonContent = super.mapToJson(updateUser);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJsonContent)).andReturn();

	}

	@Test
	public void testAddNewUserDetails() throws Exception {

		String uri = "/api/addUser";

		User newUser = new User();
		newUser.setName("TestName");
		newUser.setProfession("TestProfession");

		String inputJsonContent = super.mapToJson(newUser);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(inputJsonContent)).andReturn();
		
		
	}

	@Test
	public void testDeleteUserDetails() throws Exception {

		String uri = "/api/deleteUser/{id}";
		Long id = new Long(1);
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.delete(uri, id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andReturn();
		
		int status = result.getResponse().getStatus();
		
		System.out.println(status);
		
		Assert.assertEquals("FAILDED: Delete not possible !!", 204, status);
	}

}
