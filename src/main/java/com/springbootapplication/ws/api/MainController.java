package com.springbootapplication.ws.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbootapplication.model.User;
import com.springbootapplication.service.UserDetailService;

@Controller
public class MainController {
	
	@Autowired
	UserDetailService userDetailService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = "/api/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getAllUsers() {
		return new ResponseEntity<Collection<User>>(userDetailService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/addUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
	
		User newUser = userDetailService.create(user);
		System.out.println(newUser.toString());
		if(newUser.getId() == null){
			new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/api/updateUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		User updateUser = userDetailService.update(user);
		
		if(updateUser == null){
			new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<User>(userDetailService.update(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
		System.out.println(id);
		userDetailService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
