package com.start.bike.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.start.bike.mapper.UserMapper;
import com.start.bike.service.UserService;
import com.start.bike.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> getUsers(){
        return this.list();
    }
}
