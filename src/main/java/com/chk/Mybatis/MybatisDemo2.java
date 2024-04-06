package com.chk.Mybatis;

import com.chk.mapper.UserMapper;
import com.chk.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


// Mybatis代理开发
public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {
        // 加载mybatis核心配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行sql
//        List<User> users = sqlSession.selectList("test.selectAll");
        // 参数是一个字符串，该字符必须是映射配置文件的namespace.id

        // 获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        // 处理结果
        System.out.println(users);

        //释放资源
        sqlSession.close();
    }
}
