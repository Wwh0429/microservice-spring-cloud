package com.liuwei.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.liuwei.study.entity.User;

@RestController
public class MovieController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping("/movie/{id}")
	public User findById(@PathVariable Long id) {
		
		return this.restTemplate.getForObject("http://microservice-provider-user/simple/"+id, User.class);
	}
	
	@GetMapping("/test")
	public String loadTest() {
		
		ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
		System.out.println("111:"+serviceInstance.getHost()+":"+serviceInstance.getPort()+":"+serviceInstance.getServiceId());
		
		return "";
	}
	
}
