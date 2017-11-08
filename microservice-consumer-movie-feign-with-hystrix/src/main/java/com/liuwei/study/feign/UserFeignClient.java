package com.liuwei.study.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liuwei.study.entity.User;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {
	
	// 1.GetMapping不支持  2.@PathVariable得设置value
    @RequestMapping(method = RequestMethod.GET, value = "/simple/{id}")
    User getUser(@PathVariable("id") Long id);

    // 只要参数是复杂对象，即使指定请求方法为GET，feign依然会以POST请求发送
    @RequestMapping(method = RequestMethod.POST, value = "/user", consumes = "application/json")
    User update(@RequestBody User user);
}
