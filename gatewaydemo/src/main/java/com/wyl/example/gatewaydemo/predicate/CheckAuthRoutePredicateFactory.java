package com.wyl.example.gatewaydemo.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 */
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {

    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    private static final String KEY = "name";

    /**
     * 将获取到的参数按顺序赋值
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    /**
     * 自定义业务实现
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) { System.out.println("进入断言");

        return exchange -> {
//            System.err.println("进入了CheckAuthRoutePredicateFactory\t" + config.getName());
            if (config.getName().equals("wyl")) {
                return true;
            }
            return false;
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
