package org.mistycloud.cloud.usercenter.web.restful;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieyuejun
 * @created 2018/4/12 21:09
 */
@RestController
@RequestMapping(value = "/index", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class IndexRestful {

    @GetMapping
    public String index() {
        return "user";
    }

}
