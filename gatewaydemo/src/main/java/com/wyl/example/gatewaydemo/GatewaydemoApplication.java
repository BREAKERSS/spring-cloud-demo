package com.wyl.example.gatewaydemo;

import com.wyl.example.gatewaydemo.filter.RequestTimeGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewaydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewaydemoApplication.class, args);
    }

    /**
     * 自定义过滤器注册
     *
     * @param builder
     * @return
     */
//    @Bean
//    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
//        // @formatter:off
//        return builder.routes()
//                //注册路径
//                .route(r -> r.path("/customer/**")
//                        .filters(f -> f.filter(new RequestTimeFilter()))
//                        .uri("lb://configdemo")
//                        .order(0)
//                        .id("customer_filter_router")
//                )
//                .build();
//        // @formatter:on
//    }

    /**
     * 全局过滤器注册
     */
//    @Bean
//    public TokenFilter tokenFilter() {
//        return new TokenFilter();
//    }

    /**
     * 自定义过滤器工厂
     * @return
     */
    @Bean
    public RequestTimeGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new RequestTimeGatewayFilterFactory();
    }
}
