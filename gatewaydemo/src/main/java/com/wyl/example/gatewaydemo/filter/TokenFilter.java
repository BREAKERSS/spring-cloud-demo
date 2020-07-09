package com.wyl.example.gatewaydemo.filter;

import cn.hutool.system.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，验证token
 */
public class TokenFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
    private int i = 0;

    /**
     * 自定义过滤器业务，判断token是否存在
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("RemoteAddress地址:{}",exchange.getRequest().getRemoteAddress());
        logger.info("x-forwarded-for地址:{}",exchange.getRequest().getHeaders().get("x-forwarded-for"));
//        String token = exchange.getRequest().getQueryParams().getFirst("token");
//        if (token == null || token.isEmpty()) {
//            logger.info("token is empty...");
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
        logger.info("ip地址:{}",SystemUtil.getHostInfo().getAddress());
        logger.info("收到请求：{}", i);
        i++;
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
