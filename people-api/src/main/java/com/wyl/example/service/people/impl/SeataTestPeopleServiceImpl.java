package com.wyl.example.service.people.impl;

import com.wyl.example.mapper.SeataTestPeopleMapper;
import com.wyl.example.service.money.SeataTestMoneyService;
import com.wyl.example.service.people.SeataTestPeopleService;
import com.wyl.example.service.people.entity.SeataTestPeople;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;


/**
 * seata 测试表(SeataTestPeople)表服务实现类
 *
 * @author makejava
 * @since 2020-06-10 16:36:35
 */
@Service("seataTestPeopleService")
public class SeataTestPeopleServiceImpl implements SeataTestPeopleService {
    @Resource
    private SeataTestPeopleMapper seataTestPeopleMapper;
    @Autowired
    private SeataTestMoneyService moneyService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @PostMapping("/people/queryById")
    public SeataTestPeople queryById(String id) {
        return this.seataTestPeopleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    @PostMapping("/people/queryAllByLimit")
    public List<SeataTestPeople> queryAllByLimit(int offset, int limit) {
        return this.seataTestPeopleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param seataTestPeople 实例对象
     * @return 实例对象
     */
    @Override
    @PostMapping("/people/insert")
    public SeataTestPeople insert(SeataTestPeople seataTestPeople)throws Exception {
        if(this.seataTestPeopleMapper.insert(seataTestPeople)>0){
            throw new Exception("手动异常, 事务回滚");
        }

        return seataTestPeople;
    }

    /**
     * 修改数据
     *
     * @param seataTestPeople 实例对象
     * @return 实例对象
     */
    @Override
    @PostMapping("/people/update")
    public SeataTestPeople update(SeataTestPeople seataTestPeople) {
        this.seataTestPeopleMapper.update(seataTestPeople);
        return this.queryById(seataTestPeople.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @PostMapping("/people/deleteById")
    public boolean deleteById(String id) {
        return this.seataTestPeopleMapper.deleteById(id) > 0;
    }
}