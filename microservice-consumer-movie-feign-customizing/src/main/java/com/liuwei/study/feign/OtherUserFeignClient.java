package com.liuwei.study.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liuwei.config.FeignConfiguration2;

@FeignClient(name = "xxxx", url = "http://localhost:8761/",configuration = FeignConfiguration2.class)
public interface OtherUserFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "eureka/apps/{serviceName}")
    String getServiceInfoFromEureka(@PathVariable("serviceName") String serviceName);
}
