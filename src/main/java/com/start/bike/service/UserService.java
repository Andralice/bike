package com.start.bike.service;

import com.start.bike.entity.User;

import java.util.List;

public interface UserService {
    User selectLoginUser(User user);
    List<User> selectAllUsers(int page, int size);
    Boolean isUserExists(User user);
    void insertUser(User user);
    void updateUser(User user);
    Boolean deleteUserById(Integer userId);
}