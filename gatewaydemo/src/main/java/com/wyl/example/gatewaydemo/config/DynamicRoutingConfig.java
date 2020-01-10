package com.wyl.example.gatewaydemo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.wyl.example.gatewaydemo.entity.FilterEntity;
import com.wyl.example.gatewaydemo.entity.PredicateEntity;
import com.wyl.example.gatewaydemo.entity.RouteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 实现一个Spring提供的事件推送接口
 * 监听nacos的路由配置，实时更新路由
 */
@Component
public class DynamicRoutingConfig implements ApplicationEventPublisherAware {
    private final Logger logger = LoggerFactory.getLogger(DynamicRoutingConfig.class);
    /**
     * 路由配置文件dataId
     */
    private static final String DATA_ID = "gateway-demo.json";
    /**
     * 路由配置文件所在分组
     */
    private static final String GROUP = "DEFAULT_GROUP";
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher applicationEventPublisher;

    private static final List<String> ROUTE_LIST = new ArrayList<>();

    /**
     * 监听nacos上的路由配置文件的更新
     *
     * @throws NacosException
     */
    @PostConstruct
    public void refreshRouting() throws NacosException {
        Properties properties = new Properties();
        //配置中心地址
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        //配置中心命名空间ID
        properties.put(PropertyKeyConst.NAMESPACE, "ba53da1e-57ff-4033-9d67-b82e579ea923");
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                logger.info(configInfo);

                boolean refreshGatewayRoute = JSONObject.parseObject(configInfo).getBoolean("refreshGatewayRoute");

                if (refreshGatewayRoute) {
                    try {
                        clearRoute();
                        List<RouteEntity> list = JSON.parseArray(JSONObject.parseObject(configInfo).getString("routeList")).toJavaList(RouteEntity.class);
                        for (RouteEntity route : list) {
                            addRoute(assembleRouteDefinition(route));
                        }
                        publish();
                    } catch (Exception e) {
                        logger.error("路由更新出错", e);
                    }
                } else {
                    logger.info("路由未发生变更");
                }


            }
        });
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 清除路由
     */
    private void clearRoute() {
        for (String id : ROUTE_LIST) {
            this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
        }
        ROUTE_LIST.clear();
    }

    /**
     * 添加路由
     *
     * @param definition
     */
    private void addRoute(RouteDefinition definition) {
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            ROUTE_LIST.add(definition.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 路由刷新
     */
    private void publish() {
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    /**
     * 类型转换
     *
     * @param routeEntity
     * @return
     */
    public RouteDefinition assembleRouteDefinition(RouteEntity routeEntity) {

        RouteDefinition definition = new RouteDefinition();

        // ID
        definition.setId(routeEntity.getId());

        // Predicates
        List<PredicateDefinition> pdList = new ArrayList<>();
        for (PredicateEntity predicateEntity : routeEntity.getPredicates()) {
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setArgs(predicateEntity.getArgs());
            predicateDefinition.setName(predicateEntity.getName());
            pdList.add(predicateDefinition);
        }
        definition.setPredicates(pdList);

        // Filters
        List<FilterDefinition> fdList = new ArrayList<>();
        for (FilterEntity filterEntity : routeEntity.getFilters()) {
            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setArgs(filterEntity.getArgs());
            filterDefinition.setName(filterEntity.getName());
            fdList.add(filterDefinition);
        }
        definition.setFilters(fdList);

        // URI
        URI uri = UriComponentsBuilder.fromUriString(routeEntity.getUri()).build().toUri();
        definition.setUri(uri);

        return definition;
    }
}
