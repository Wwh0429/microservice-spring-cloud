package com.liuwei.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.liuwei.study.entity.User;
import com.liuwei.study.feign.OtherUserFeignClient;
import com.liuwei.study.feign.UserFeignClient;

@RestController
public class MovieController {
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private OtherUserFeignClient otherUserFeignClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		return userFeignClient.getUser(id);
	}
		
	@GetMapping("/{serviceName}")
	public String getServiceInfoFromEureka(@PathVariable String serviceName) {
		
		return otherUserFeignClient.getServiceInfoFromEureka(serviceName);
	}
}
