package com.wyl.example.elasticsearch;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsServiceImpl implements EsService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findByList() {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not

        //设置模糊搜索,博客的简诉中有学习两个字
        builder.must(QueryBuilders.fuzzyQuery("title", "1"));

        //设置要查询博客的标题中含有关键字
//        builder.must(new QueryStringQueryBuilder("man").field("springdemo"));

        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("price").order(SortOrder.DESC);

        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
//        PageRequest page = new PageRequest(0, 10);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(PageRequest.of(0, 10));
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        System.out.println(nativeSearchQueryBuilder.toString());
        //3.执行方法1
        Page<Item> page = itemRepository.search(query);
        //执行方法2：注意，这儿执行的时候还有个方法那就是使用elasticsearchTemplate
        //执行方法2的时候需要加上注解
        //@Autowired
        //private ElasticsearchTemplate elasticsearchTemplate;
        List<Item> blogList = elasticsearchTemplate.queryForList(query, Item.class);

        //4.获取总条数(用于前端分页)
        int total = (int) page.getTotalElements();

        //5.获取查询到的数据内容（返回给前端）
        List<Item> content = page.getContent();

        return content;
    }
}
