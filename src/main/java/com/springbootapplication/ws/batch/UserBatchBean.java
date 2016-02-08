package com.springbootapplication.ws.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springbootapplication.model.User;
import com.springbootapplication.service.UserDetailService;

@Component
public class UserBatchBean {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDetailService userDetailService;
	
	@Scheduled(
			cron="0,30 * * * * *")
	public void cronJob(){
		logger.info("> Cron Job Started: ");
		
		Collection<User> userList = userDetailService.findAll();
		
		logger.info("< Cron Job Ended: ");
	}

}
