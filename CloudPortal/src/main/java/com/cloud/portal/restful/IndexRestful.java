package com.cloud.portal.restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 0:58
 * Description:
 */
@RefreshScope
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexRestful {

    @Value("${config.value}")
    private String config;

    @GetMapping("/config")
    public String config() {
        return config;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/client")
    public List<Map<String, String>> getMetadata(String serviceId) {
        return discoveryClient.getInstances(serviceId).parallelStream().map(instance -> instance.getMetadata()).collect(Collectors.toList());
    }

    @GetMapping("/cloudConfig")
    public String cloudConfig() throws InterruptedException {
        Thread.sleep(1500);
        return config;
    }
}
