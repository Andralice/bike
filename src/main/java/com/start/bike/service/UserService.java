package com.start.bike.service;

import com.start.bike.entity.User;

public interface UserService {
    User selectLoginUser(User user);
    Boolean isUserExists(User user);
    void insertUser(User user);
    User updateUser(User user);
    User selectUserByPassword(User user);
    Boolean deleteUser(User user);
}