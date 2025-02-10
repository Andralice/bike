package com.start.bike.service.impl;

import com.start.bike.entity.User;
import com.start.bike.mapper.UserMapper;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Object selectUser(String username, String password) {
        return userMapper.selectUser(username, password);
    }

    @Override
    public Boolean isExistUser(String username) {
        return userMapper.isUserExists(username);
    }

    @Override
    public void insertUser(String username, String password) {
        userMapper.insertUser(username, password);
    }
}