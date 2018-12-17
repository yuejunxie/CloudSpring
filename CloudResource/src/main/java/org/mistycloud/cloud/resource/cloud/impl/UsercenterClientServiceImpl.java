package org.mistycloud.cloud.resource.cloud.impl;

import org.mistycloud.cloud.resource.cloud.UsercenterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xieyuejun
 * @created 2018/4/12 17:11
 */
@Service
public class UsercenterClientServiceImpl implements UsercenterClientService {


    public static String instance = "";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String index() {
        HttpHeaders hh = new HttpHeaders();
        //加入bic校验头
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request != null) {
                //获取header信息
                String cookie = request.getHeader(HttpHeaders.COOKIE);
                if (cookie != null) {
                    hh.set(HttpHeaders.COOKIE, cookie);
                }
            }
        }
        hh.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<?> httpEntity = new HttpEntity<>(null, hh);

        Object body = restTemplate.exchange("http://usercenter/uc/index", HttpMethod.GET,
                new HttpEntity<>(null), String.class).getBody();

        return String.valueOf(body);

    }
}
