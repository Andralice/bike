package com.start.bike.service.impl;

import com.start.bike.entity.Log;
import com.start.bike.entity.UserLog;
import com.start.bike.mapper.UserLogMapper;
import com.start.bike.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public void insertUserLog(UserLog log) {
        userLogMapper.insertUserLog(log);
    }

    @Override
    public List<UserLog> selectUserLog(int entityId, String entityType) {
        return userLogMapper.selectUserLog(entityId,entityType);
    }

    @Override
    public UserLog selectUserLogById(int userLogId) {
        return userLogMapper.selectUserLogById(userLogId);
    }
}
