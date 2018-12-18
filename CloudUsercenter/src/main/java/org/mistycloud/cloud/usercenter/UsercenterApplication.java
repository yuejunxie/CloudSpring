package org.mistycloud.cloud.usercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xieyuejun
 * @created 2018/4/12 12:56
 */
@EnableScheduling
//@EnableFeignClients
//@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class UsercenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterApplication.class, args);
    }
}
