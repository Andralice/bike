package com.start.bike.mapper;

import com.start.bike.entity.UserLog;

import java.util.List;

public interface UserLogMapper {
    void insertUserLog(UserLog userLog);
    List<UserLog> selectUserLog(int entityId,String entityType);
    UserLog selectUserLogById(int userLogId);
}
