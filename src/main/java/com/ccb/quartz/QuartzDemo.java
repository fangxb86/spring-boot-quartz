package com.ccb.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.ccb.service.UsersService;
 
 
@EnableScheduling
@Component
public class QuartzDemo {

	@Autowired
	private UsersService usersService;

	public void task(){
		usersService.addUser();
	  }
}
