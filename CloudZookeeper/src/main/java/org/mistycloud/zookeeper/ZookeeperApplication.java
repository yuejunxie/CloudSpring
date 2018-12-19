package org.mistycloud.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * CloudSpring
 * Created: 2018/12/18 21:37
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperApplication.class, args);
    }
}
