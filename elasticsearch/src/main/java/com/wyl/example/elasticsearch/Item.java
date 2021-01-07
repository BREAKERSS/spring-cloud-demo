package com.wyl.example.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "wyl_item",type = "docs", shards = 1, replicas = 0)
@Data
public class Item {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title; //标题

    @Field(type = FieldType.Keyword)
    private String category;// 分类

    @Field(type = FieldType.Keyword)
    private String brand; // 品牌

    @Field(type = FieldType.Double)
    private Double price; // 价格
}
