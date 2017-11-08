package com.liuwei.study.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.liuwei.config.FeignConfiguration;
import com.liuwei.study.entity.User;

import feign.Param;
import feign.RequestLine;

@FeignClient(name = "microservice-provider-user",configuration = FeignConfiguration.class)
public interface UserFeignClient {

    @RequestLine("GET /simple/{id}")
    User getUser(@Param("id") Long id);

}
