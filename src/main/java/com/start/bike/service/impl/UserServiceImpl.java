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
    public User selectUser(User user) {
        try {
            User ceshi1 = userMapper.selectUser(user);
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
//            userMapper.insertUser(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getPosition(),
//                    user.getTelephone(),
//                    user.getWorkPlace(),
//                    user.getRole()
//            );
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
            return userMapper.selectUser(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int deleteUser(User user) {
        try {
            int result = userMapper.deleteUser(user);
            if (result > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}