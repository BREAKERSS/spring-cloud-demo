package com.wyl.example.service.people.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * seata 测试表(SeataTestPeople)实体类
 *
 * @author makejava
 * @since 2020-06-10 16:36:33
 */
@Data
public class SeataTestPeople implements Serializable {
    private static final long serialVersionUID = 641753496403216568L;
    /**
    * 主键
    */
    private String id;
    /**
    * 名称
    */
    private String name;



}