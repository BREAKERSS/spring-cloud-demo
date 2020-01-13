package com.wyl.example.gatewaydemo.entity;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器实体类
 */
@Data
public class FilterEntity {
    /**
     * 过滤器对应的Name
     */
    private String name;

    /**
     * 路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}
