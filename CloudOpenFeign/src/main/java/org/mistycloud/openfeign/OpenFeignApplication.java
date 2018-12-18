package org.mistycloud.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieyuejun
 * @created 2018/12/18 11:01
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class OpenFeignApplication {

    @Autowired
    HelloClient client;

    public static void main(String[] args) {
        SpringApplication.run(OpenFeignApplication.class, args);
    }

    @RequestMapping("/")
    public String hello() {
        return client.hello();
    }

    @FeignClient("usercenter")
    interface HelloClient {
        @RequestMapping(value = "/uc/index", method = RequestMethod.GET)
        String hello();
    }

//    Server端

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String helloG() {
        return "hello";
    }

}

