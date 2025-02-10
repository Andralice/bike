package com.start.bike.service;

import com.start.bike.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User selectUser(String username, String password);
    Boolean isUserExists(String username);
    void insertUser(String username, String password);
    User updateUser(String username, String password);
    User selectUserByPassword(@Param("username") String username);
    Boolean deleteUser(String username);
}