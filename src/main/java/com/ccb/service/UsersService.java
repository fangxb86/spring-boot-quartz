package com.ccb.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class UsersService {

	public void addUser() {
		System.out.println("add users.................."+new Date());
	}
	public void addUser2() {
		System.out.println("add users2.................."+new Date());
	}
}
