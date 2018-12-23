package org.mistycloud.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author xieyuejun
 * @created 2018/4/12 14:58
 */
//配置中心
@EnableConfigServer
//服务注册
@EnableDiscoveryClient
@SpringBootApplication
public class CloudConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigApplication.class, args);
    }
}
