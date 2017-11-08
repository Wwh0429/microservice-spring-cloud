package com.liuwei.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.liuwei.study.entity.User;
import com.liuwei.study.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("/simple/{id}") 
	public User findById(@PathVariable Long id) {	
		return userRepository.findOne(id);
	}
	
	@PostMapping("/user") 
	public User getUser(@RequestBody User user) {		
		return user;
	}
	
	@GetMapping("/eureka-instance")
	public String serviceUrl() {
	    InstanceInfo instance = eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
	    return instance.getHomePageUrl();
	}
	
	@GetMapping("/instance-info")
	public ServiceInstance serviceInfo() {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
	    return instance;
	}
	
	@GetMapping("/list-all")
	public List<User> listAll() {
		
		List<User> list = Lists.newArrayList();
		User user1 = new User(1L,"zhangsan");
		User user2 = new User(2L,"lisi");
		list.add(user1);
		list.add(user2);
		return list;
	}
}
