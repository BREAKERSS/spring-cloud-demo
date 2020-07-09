package com.wyl.example.service.money;

import com.wyl.example.service.money.entity.SeataTestMoney;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * (SeataTestMoney)表服务接口
 *
 * @author makejava
 * @since 2020-06-10 17:38:22
 */
@FeignClient(name = "money-api", contextId = "testMoney")
public interface SeataTestMoneyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @PostMapping("/money/queryById")
    SeataTestMoney queryById(@RequestParam  String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @PostMapping("/money/queryAllByLimit")
    List<SeataTestMoney> queryAllByLimit(@RequestParam int offset,@RequestParam int limit);

    /**
     * 新增数据
     *
     * @param seataTestMoney 实例对象
     * @return 实例对象
     */
    @PostMapping("/money/insert")
    SeataTestMoney insert(@RequestBody SeataTestMoney seataTestMoney);

    /**
     * 修改数据
     *
     * @param seataTestMoney 实例对象
     * @return 实例对象
     */
    @PostMapping("/money/update")
    SeataTestMoney update(@RequestBody SeataTestMoney seataTestMoney);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @PostMapping("/money/deleteById")
    boolean deleteById(@RequestParam String id);

}