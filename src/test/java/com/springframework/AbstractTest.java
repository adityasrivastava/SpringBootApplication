package com.springframework;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springbootapplication.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
		classes = Application.class)
public abstract class AbstractTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

}
