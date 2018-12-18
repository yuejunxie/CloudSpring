package com.mistycloud.consul;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author xieyuejun
 * @created 2018/12/18 15:52
 */
@SpringBootApplication
//@EnableFeignClients
public class ConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulApplication.class, args);
    }

//    @Autowired
//    private LoadBalancerClient loadBalancer;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    private SampleClient sampleClient;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private Registration registration;
//
//    @Value("${spring.application.name:testConsulApp}")
//    private String appName;
//
//    @RequestMapping("/me")
//    public ServiceInstance me() {
//        return this.registration;
//    }
//
//    @RequestMapping("/")
//    public ServiceInstance lb() {
//        return loadBalancer.choose(appName);
//    }
//
//    @RequestMapping("/rest")
//    public String rest() {
//        return this.restTemplate.getForObject("http://" + appName + "/me", String.class);
//    }
//
//    @RequestMapping("/choose")
//    public String choose() {
//        return loadBalancer.choose(appName).getUri().toString();
//    }
//
//    @RequestMapping("/myenv")
//    public String env(@RequestParam("prop") String prop) {
//        return env.getProperty(prop, "Not Found");
//    }
//
//    @RequestMapping("/instances")
//    public List<ServiceInstance> instances() {
//        return discoveryClient.getInstances(appName);
//    }
//
//    @RequestMapping("/feign")
//    public String feign() {
//        return sampleClient.choose();
//    }
//
//	/*@Bean
//	public SubtypeModule sampleSubtypeModule() {
//		return new SubtypeModule(SimpleRemoteEvent.class);
//	}*/
//
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//
//	/*@Override
//	public void onApplicationEvent(SimpleRemoteEvent event) {
//		log.info("Received event: {}", event);
//	}*/
//
//    @FeignClient("consul")
//    public interface SampleClient {
//
//        @RequestMapping(value = "/choose", method = RequestMethod.GET)
//        String choose();
//    }

}
