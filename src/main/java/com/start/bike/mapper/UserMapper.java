package com.start.bike.mapper;

import com.start.bike.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User selectUser(@Param("username") String username, @Param("password") String password);

    Boolean isUserExists(@Param("username") String username);

    void insertUser(@Param("username") String username, @Param("password") String password);
}