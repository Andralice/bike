package com.start.bike.mapper;

import com.start.bike.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 根据用户名查询用户
    User selectLoginUser( User user);
    List<User> selectAllUsers(int page, int size);
    // 判断用户是否存在
    Boolean isUserExists(User user);
    // 插入用户
    void insertUser(User user);
    // 更新用户
    void updateUser(User user);
    // 查询用户密码
    User selectUserByPassword(User user);
    // 删除用户
    int deleteUserById(Integer userId);
}