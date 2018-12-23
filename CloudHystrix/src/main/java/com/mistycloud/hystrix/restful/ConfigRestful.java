package com.mistycloud.hystrix.restful;

import com.mistycloud.hystrix.feign.PortalFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 13:53
 * Description:
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ConfigRestful {

    @Autowired
    private PortalFeign portalFeign;

    @GetMapping("/config")
    public String config() {
        return portalFeign.config();
    }

    /*
    @HystrixCommand注解
            默认开启线程池隔离方式
            默认开启服务降级，通过fallbackMethod指定
     */
    @GetMapping("/cloudConfig")
    @HystrixCommand(fallbackMethod = "tip", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"))
//    @HystrixCommand(groupKey="UserGroup", commandKey = "GetUserByIdCommand",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),//指定多久超时，单位毫秒。超时进fallback
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),//判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
//            },
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "30"),
//                    @HystrixProperty(name = "maxQueueSize", value = "101"),
//                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
//                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
//                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
//            })
    public String cloudConfig() {
        return portalFeign.cloudConfig();
    }

    public String tip() {
        return "Portal超时";
    }

}
