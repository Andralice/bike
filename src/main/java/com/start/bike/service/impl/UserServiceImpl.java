package com.start.bike.service.impl;

import com.start.bike.entity.User;
import com.start.bike.mapper.UserMapper;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectLoginUser(User user) {
        try {
            User ceshi1 = userMapper.selectLoginUser(user);
            return ceshi1;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean isUserExists(User user) {
        return userMapper.isUserExists(user);
    }

    @Override
    public void insertUser(User user) {
        try {
            userMapper.insertUser(user);
        } catch (Exception ignored) {

        }
    }

    @Override
    public User selectUserByPassword(User user) {
        try {
            userMapper.selectUserByPassword(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public User updateUser(User user) {
        try {
            userMapper.updateUser(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean deleteUser(User user) {
        int result = userMapper.deleteUser(user);
        return result > 0;
    }
}