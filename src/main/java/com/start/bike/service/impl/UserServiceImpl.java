package com.start.bike.service.impl;

import com.start.bike.entity.User;
import com.start.bike.mapper.UserMapper;
import com.start.bike.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String username, String password) {
        logger.info("开始查询用户: username={}, password=******", username);
        try {
            User user = userMapper.selectUser(username, password);
            logger.info("查询用户成功: {}", user);
            return user;
        } catch (Exception e) {
            logger.error("查询用户失败: username={}, error={}", username, e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean isUserExists(String username) {
        logger.info("检查用户是否存在: username={}", username);
        return userMapper.isUserExists(username);
    }

    @Override
    public void insertUser(String username, String password) {
        logger.info("开始插入用户: username={}, password=******", username);
        try {
            userMapper.insertUser(username, password);
            logger.info("插入用户成功: username={}", username);
        } catch (Exception e) {
            logger.error("插入用户失败: username={}, error={}", username, e.getMessage());
        }
    }

    @Override
    public User selectUserByPassword(String username) {
        logger.info("开始查询用户: username={}", username);
        try {
            User user = userMapper.selectUserByPassword(username);
            logger.info("查询用户成功: {}", user);
            return user;
        } catch (Exception e) {
            logger.error("查询用户失败: username={}, error={}", username, e.getMessage());
            return null;
        }
    }
    @Override
    public User updateUser(String username, String password) {
        logger.info("开始更新用户: username={}, password=******", username);
        try {
            userMapper.updateUser(username, password);
            logger.info("更新用户成功: username={}", username);
            return userMapper.selectUser(username, password);
        } catch (Exception e) {
            logger.error("更新用户失败: username={}, error={}", username, e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteUser(String username) {
        logger.info("开始删除用户: username={}", username);
        try {
            int result = userMapper.deleteUser(username);
            if (result > 0) {
                logger.info("删除用户成功: username={}", username);
                return true;
            } else {
                logger.warn("删除用户失败: 用户不存在, username={}", username);
                return false;
            }
        } catch (Exception e) {
            logger.error("删除用户失败: username={}, error={}", username, e.getMessage());
            return false;
        }
    }
}