package com.chk.mapper;

import com.chk.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    /**
     * 查询所有
     */
    List<Brand> selectAll();

    /**
     * 查询详情：根据id查询
     */
    Brand selectById(int id);

    /**
     * 多条件查询
     */
//    方式一：
//    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

//    方式二：
//    List<Brand> selectByCondition(Brand brand);

//    方式三：
    List<Brand> selectByCondition(Map map);

    /**
     * 单条件动态查询
     */
    List<Brand> selectByConditionSingle(Brand brand);

    int update(Brand brand);

    void add(Brand brand);

    void deleteById(int id);
}
