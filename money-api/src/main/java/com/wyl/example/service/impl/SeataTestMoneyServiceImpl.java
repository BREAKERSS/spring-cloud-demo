package com.wyl.example.service.impl;

import com.wyl.example.mapper.SeataTestMoneyMapper;
import com.wyl.example.service.money.SeataTestMoneyService;
import com.wyl.example.service.money.entity.SeataTestMoney;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


/**
 * (SeataTestMoney)表服务实现类
 *
 * @author makejava
 * @since 2020-06-10 17:43:24
 */
@Service("seataTestMoneyService")
public class SeataTestMoneyServiceImpl implements SeataTestMoneyService {
    @Resource
    private SeataTestMoneyMapper seataTestMoneyMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @PostMapping("/money/queryById")
    public SeataTestMoney queryById(@RequestParam String id) {
        return this.seataTestMoneyMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    @PostMapping("/money/queryAllByLimit")
    public List<SeataTestMoney> queryAllByLimit(@RequestParam int offset,@RequestParam int limit) {
        return this.seataTestMoneyMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param seataTestMoney 实例对象
     * @return 实例对象
     */
    @Override
    @PostMapping("/money/insert")
    public SeataTestMoney insert(@RequestBody SeataTestMoney seataTestMoney) {
        this.seataTestMoneyMapper.insert(seataTestMoney);
        return seataTestMoney;
    }

    /**
     * 修改数据
     *
     * @param seataTestMoney 实例对象
     * @return 实例对象
     */
    @Override
    @PostMapping("/money/update")
    public SeataTestMoney update(@RequestBody SeataTestMoney seataTestMoney) {
        this.seataTestMoneyMapper.update(seataTestMoney);
        return this.queryById(seataTestMoney.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @PostMapping("/money/deleteById")
    public boolean deleteById(@RequestParam String id) {
        return this.seataTestMoneyMapper.deleteById(id) > 0;
    }
}