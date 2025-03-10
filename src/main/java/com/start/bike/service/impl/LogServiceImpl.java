package com.start.bike.service.impl;

import com.start.bike.entity.Log;
import com.start.bike.mapper.LogMapper;
import com.start.bike.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;


    @Override
    public void insertLog(Log log) {
        logMapper.insertLog(log);
    }
}
