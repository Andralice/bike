package com.start.bike.service.impl;

import com.start.bike.entity.User;
import com.start.bike.mapper.UserMapper;
import com.start.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectLoginUser(User user) {
        return userMapper.selectLoginUser(user);
    }

    @Override
    public List<User> selectAllUsers(int page, int Size) {
        return userMapper.selectAllUsers(page, Size);
    }


    @Override
    public Boolean isUserExists(User user) {
        return userMapper.isUserExists(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public Boolean deleteUserById(Integer userId) {
        int result = userMapper.deleteUserById(userId);
        return result > 0;
    }
}