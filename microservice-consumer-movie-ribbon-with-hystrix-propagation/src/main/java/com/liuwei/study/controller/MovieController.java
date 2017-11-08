package com.liuwei.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

import com.liuwei.study.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/movie/{id}")
	// 建议 commandProperties 先不配置，等系统抛异常再行配置
	@HystrixCommand(fallbackMethod = "findByIdFallback",commandProperties = @HystrixProperty(name = "execution.isolation.strategy" , value = "SEMAPHORE"))
	public User findById(@PathVariable Long id) {
		return this.restTemplate.getForObject("http://microservice-provider-user/simple/"+id, User.class);
	}
	
	public User findByIdFallback(Long id) {
		
		User user = new User();
		user.setId(0L);
		return user;
	}

}
