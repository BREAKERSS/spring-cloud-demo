package com.wyl.example.mapper;

import com.wyl.example.service.people.entity.SeataTestPeople;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * seata 测试表(SeataTestPeople)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-10 16:55:22
 */
public interface SeataTestPeopleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SeataTestPeople queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SeataTestPeople> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param seataTestPeople 实例对象
     * @return 对象列表
     */
    List<SeataTestPeople> queryAll(SeataTestPeople seataTestPeople);

    /**
     * 新增数据
     *
     * @param seataTestPeople 实例对象
     * @return 影响行数
     */
    int insert(SeataTestPeople seataTestPeople);

    /**
     * 修改数据
     *
     * @param seataTestPeople 实例对象
     * @return 影响行数
     */
    int update(SeataTestPeople seataTestPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}