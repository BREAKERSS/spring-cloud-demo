package com.wyl.example.gatewaydemo.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 自定义拦截器
 * @author wyl
 */
public class RequestTimeFilter implements GatewayFilter, Ordered {
    private static final Log log = LogFactory.getLog(RequestTimeFilter.class);
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    /**
     * 自定义拦截业务，输出请求所需时间
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Map<String, String> uriVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);

        ServerHttpResponse response = exchange.getResponse();

        if (exchange.getRequest().getPath().toString().endsWith("Port")) {
            byte[] datas = "被拦截啦".getBytes();
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }

        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })

        );

    }

    /**
     * 用于设定过滤器优先级别，值越大则优先级越低
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }


}
