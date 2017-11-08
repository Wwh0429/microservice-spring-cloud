package com.liuwei.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.liuwei.study.feign.UserFeignClient;
import com.liuwei.study.entity.User;

@RestController
public class MovieController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return userFeignClient.getUser(id);
	}
	
	@GetMapping("/user")
	public User getuser(User user) {
		
		return userFeignClient.update(user);
	}

}
