package com.start.bike.mapper;

import com.start.bike.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    User selectUser(@Param("username") String username, @Param("password") String password);
    // 判断用户是否存在
    Boolean isUserExists(@Param("username") String username);
    // 插入用户
    void insertUser(@Param("username") String username, @Param("password") String password);
    // 更新用户
    User updateUser(@Param("username") String username, @Param("password") String password);
    // 查询用户密码
    User selectUserByPassword(@Param("username") String username);
    // 删除用户
    int deleteUser(@Param("username") String username);
}