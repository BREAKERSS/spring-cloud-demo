package com.wyl.example.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elasticsearch")
@RefreshScope
public class EsController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private EsService esService;

    @GetMapping("/create")
    public void testCreate() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);
    }

    @PostMapping("/save")
    public void save(@RequestBody Item item) {
        esService.save(item);
    }
    @PostMapping("/findByList")
    public List<Item> findByList() {
        return esService.findByList();
    }
}
