package com.wyl.example.elasticsearch;

import java.util.List;

public interface EsService {
    void save(Item item);
    List<Item> findByList();
}
