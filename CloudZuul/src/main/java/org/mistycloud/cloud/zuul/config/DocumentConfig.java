package org.mistycloud.cloud.zuul.config;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: JackyShieh
 * Corporation: CornerStone LTD
 * WE LINK
 * cloud-spring
 * Created: 2018/12/23 23:19
 * Description:
 */
@Primary
@Component
public class DocumentConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;

    public DocumentConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> System.out.println(route));
        routes.forEach(route -> resources.add(buildSwaggerResource(route.getId(), "/" + route.getId() + route.getFullPath().replace("/**", "/v2/api-docs"))));
        return resources;
    }

//    public List<SwaggerResource> get() {
//        List<SwaggerResource> swaggerResourceList = new ArrayList<>();
//        swaggerResourceList.add(buildSwaggerResource("portal", "/portal/portal/v2/api-docs"));
//        swaggerResourceList.add(buildSwaggerResource("configCenter", "/configCenter/v2/api-docs"));
//        return swaggerResourceList;
//    }

    private SwaggerResource buildSwaggerResource(String name, String url) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(url);
        swaggerResource.setSwaggerVersion("2.9.2");
        return swaggerResource;
    }
}
