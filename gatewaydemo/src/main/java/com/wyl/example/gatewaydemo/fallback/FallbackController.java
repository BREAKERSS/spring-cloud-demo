package com.wyl.example.gatewaydemo.fallback;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wyl
 * fallbackcontroller
 */
@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @RequestMapping("")
    public String fallback() {
        return "error";
    }

    @RequestMapping(value = "/fallback")
    @ResponseStatus
    public Mono<Map<String, Object>> fallback(ServerWebExchange exchange, Throwable throwable) {
        Map<String, Object> result = new HashMap<>(8);
        ServerHttpRequest request = exchange.getRequest();
        result.put("path", request.getPath().pathWithinApplication().value());
        result.put("method", request.getMethodValue());
        if (null != throwable.getCause()) {
            result.put("message", throwable.getCause().getMessage());
        } else {
            result.put("message", throwable.getMessage());
        }
        return Mono.just(result);
    }

    public static void main(String[] args) {
        Flux.just("Hello", "World").subscribe(System.out::println);
    }
}
