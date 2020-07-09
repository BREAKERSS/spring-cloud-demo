package com.wyl.example.service.money.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (SeataTestMoney)实体类
 *
 * @author makejava
 * @since 2020-06-10 17:38:21
 */
@Data
public class SeataTestMoney implements Serializable {
    private static final long serialVersionUID = 684744706079302672L;
    /**
    * 主键
    */
    private String id;
    /**
    * 存款
    */
    private Double money;

}