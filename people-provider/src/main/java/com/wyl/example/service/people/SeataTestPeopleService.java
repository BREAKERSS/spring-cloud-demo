package com.wyl.example.service.people;

import com.wyl.example.service.people.entity.SeataTestPeople;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * seata 测试表(SeataTestPeople)表服务接口
 *
 * @author makejava
 * @since 2020-06-10 16:36:35
 */
@FeignClient(name = "people-api", contextId = "testPeople")
public interface SeataTestPeopleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @PostMapping("/people/queryById")
    SeataTestPeople queryById(@RequestParam String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @PostMapping("/people/queryAllByLimit")
    List<SeataTestPeople> queryAllByLimit(@RequestParam int offset,@RequestParam int limit);

    /**
     * 新增数据
     *
     * @param seataTestPeople 实例对象
     * @return 实例对象
     */
    @PostMapping("/people/insert")
    SeataTestPeople insert(@RequestBody SeataTestPeople seataTestPeople)throws Exception;

    /**
     * 修改数据
     *
     * @param seataTestPeople 实例对象
     * @return 实例对象
     */
    @PostMapping("/people/update")
    SeataTestPeople update(@RequestBody SeataTestPeople seataTestPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @PostMapping("/people/deleteById")
    boolean deleteById(@RequestParam String id);

}