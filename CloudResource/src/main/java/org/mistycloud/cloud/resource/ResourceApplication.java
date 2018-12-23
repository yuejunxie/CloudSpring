package org.mistycloud.cloud.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * @author xieyuejun
 * @created 2018/4/12 17:07
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
