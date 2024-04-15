package com.chk.test;

import com.chk.mapper.BrandMapper;
import com.chk.mapper.UserMapper;
import com.chk.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {


    @Test
    public void SelectAll() throws IOException {
        // 1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        // 5.释放资源
        sqlSession.close();
    }

    @Test
    public void SelectById() throws IOException {
        // 1. 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 获取参数
        int id = 1;

        // 4. 执行方法
        Brand brand = brandMapper.selectById(id);
        System.out.println(brand);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        //1. 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //4. 执行方法
        // 方式一：接口方法参数使用@Param方式调用的方法
        //List<Brand> brands = brandMapper.selectByCondition (status, companyName, brandName);

        // 方式二：接口方法参数是 实体类对象 方式调用的方法
//        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        // 方式三：接口方法参数是 map集合对象 方式调用的方法

        Map map = new HashMap();
//        map.put("status",  status);
        map.put("companyName", companyName);
        map.put("brandName", brandName);
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);

        //5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
        // 接收参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);


        //1. 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3. 获取Mapper接口代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);

        //5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //接收参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int ordered = 100;

        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        // 1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSession sqlSession = sqlSessionFactory.openSession(true);  //自动提交事务 不用再手动提交

        // 3. 获取BrandMapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        brandMapper.add(brand);
        Integer id = brand.getId();
        System.out.println(id);

        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
    }

    @Test
    public void testUpate() throws IOException {
        // 接收参数
        int status = 1;
//        String companyName = "波导手机";
//        String brandName = "波导手机";
//        String description = "波导手机，手机中的战斗机";
//        int ordered = 200;
        int id = 7;

        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setDescription(description);
//        brand.setOrdered(ordered);
        brand.setId(id);

        // 1. 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取BrandMapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        int count = brandMapper.update(brand);
        System.out.println(count);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        // 1. 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取BrandMapper代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int id = 8;

        // 4. 执行方法
        brandMapper.deleteById(id);


        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        String recource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(recource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int[] ids = {9, 10, 11};

        brandMapper.deleteByIds(ids);

        sqlSession.commit();

        sqlSession.close();
    }
}

