package com.start.bike.service;

import com.start.bike.entity.User;

public interface UserService {
    Object selectUser(String username, String password);
    Boolean isExistUser(String username);
    void insertUser(String username, String password);
}