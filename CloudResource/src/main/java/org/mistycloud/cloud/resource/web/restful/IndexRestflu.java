package org.mistycloud.cloud.resource.web.restful;

import org.mistycloud.cloud.resource.cloud.UsercenterClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author xieyuejun
 * @created 2018/4/12 17:09
 */
@RestController
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexRestflu {

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private UsercenterClientService usercenterClientService;

    @GetMapping
    public String index() {
        return usercenterClientService.index();
    }
}
