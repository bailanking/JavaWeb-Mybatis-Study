package com.chk.mapper;

import com.chk.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User select(@Param("username") String username,  @Param("password") String password);

    /**
     * 使用注解实现CURD
     * @param id
     * @return
     */

    @Select("select * from tb_user where id = #{id}")
    User selectById(int id);
}
