package com.wyl.example.gatewaydemo.predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 */
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    private final Logger logger = LoggerFactory.getLogger(CheckAuthRoutePredicateFactory.class);

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    private static final String KEY = "name";
    private int i = 0;

    /**
     * 将获取到的参数按顺序赋值
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    /**
     * 自定义业务实现
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        System.out.println("进入断言");
        logger.info("收到请求{}", i);
        i++;
        return exchange -> {
//            System.err.println("进入了CheckAuthRoutePredicateFactory\t" + config.getName());
            return true;
        };
    }

    /**
     * 用于接受一个自定义参数
     */
    static class Config {
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
