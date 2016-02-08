package com.springbootapplication.service;

import java.util.concurrent.Future;

import com.springbootapplication.model.User;

public interface EmailService {
	
	Boolean send(User user);
	void sendAsync(User user);
	Future<Boolean> sendAsyncWithResult(User user);

}
