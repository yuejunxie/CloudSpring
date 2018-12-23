package com.mistycloud.hystrix.feign;

import com.mistycloud.hystrix.fallback.PortalFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 13:55
 * Description:
 */
@FeignClient(name = "PORTAL", path = "/portal", fallback = PortalFallBack.class)
public interface PortalFeign {

    @GetMapping("/cloudConfig")
    String cloudConfig();

    @GetMapping("/config")
    String config();

}
