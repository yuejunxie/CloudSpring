package com.mistycloud.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 23:19
 * Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.mistycloud.portal"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("基石云门户接口")
                .extensions(Arrays.asList(new StringVendorExtension("基石科技有限公司","17816861431")))
                .contact(new Contact("基石科技有限公司", "www.cornerstone.com", "yuejunxie@163,com"))
                .description("基石云门户接口Swagger文档").termsOfServiceUrl("")
                .version("1.0.0-SNAPSHOT").license("基石云协议").build();
    }
}
