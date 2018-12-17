package org.mistycloud.cloud.resource.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServletContextConfiguration {


    @Bean
    public ServletContextInitializer servletContextInitializer() {
        ServletContextInitializer sc = servletContext -> {
            // 时区和地区检查
            // TimezoneUtil.checkAndSetLocaleTimeZone(timezoneConfigProperties.getLocale(), timezoneConfigProperties.getTimezone());

        };
        return sc;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
