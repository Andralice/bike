package com.start.bike.service;

import com.start.bike.entity.Log;
import com.start.bike.entity.UserLog;

import java.util.List;

public interface UserLogService {
    void insertUserLog(UserLog userLog);
    List<UserLog> selectUserLog(int entityId,String entityType);
    UserLog selectUserLogById(int userLogId);
}
