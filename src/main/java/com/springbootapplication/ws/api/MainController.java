package com.springbootapplication.ws.api;

import java.util.Collection;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootapplication.model.User;
import com.springbootapplication.service.EmailService;
import com.springbootapplication.service.UserDetailService;

@Controller
public class MainController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDetailService userDetailService;
	
	@Autowired
	EmailService emailService;

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

	@RequestMapping(value = "/api/deleteUser/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
		userDetailService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/api/getUser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserDetails(@PathVariable("id") Long id){
		User getUser = userDetailService.findOne(id);
		return new ResponseEntity<User>(getUser, HttpStatus.OK);
	}
	
	
	@RequestMapping(
            value = "/api/users/{id}/send",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> sendGreeting(@PathVariable("id") Long id,
            @RequestParam(
                    value = "wait",
                    defaultValue = "false") boolean waitForAsyncResult) {

        logger.info("> sendGreeting id:{}", id);

        User user = null;

        try {
            user = userDetailService.findOne(id);
            if (user == null) {
                logger.info("< sendGreeting id:{}", id);
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }

            if (waitForAsyncResult) {
                Future<Boolean> asyncResponse = emailService
                        .sendAsyncWithResult(user);
                boolean emailSent = asyncResponse.get();
                logger.info("- greeting email sent? {}", emailSent);
            } else {
                emailService.sendAsync(user);
            }
        } catch (Exception e) {
            logger.error("A problem occurred sending the Greeting.", e);
            return new ResponseEntity<User>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.info("< sendGreeting id:{}", id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
