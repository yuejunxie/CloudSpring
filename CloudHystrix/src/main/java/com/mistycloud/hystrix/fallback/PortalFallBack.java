package com.mistycloud.hystrix.fallback;

import com.mistycloud.hystrix.feign.PortalFeign;
import org.springframework.stereotype.Component;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 15:27
 * Description:
 */
@Component
public class PortalFallBack implements PortalFeign {

    @Override
    public String cloudConfig() {
        return "超时或异常";
    }

    @Override
    public String config() {
        return "超时或异常";
    }
}
