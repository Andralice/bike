package com.start.bike.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.start.bike.entity.UserLog;
import com.start.bike.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLogUtil {

    private final ObjectMapper objectMapper;

    @Autowired
    private UserLogService userLogService;

    // 构造函数注入并初始化 ObjectMapper
    public UserLogUtil() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * 记录用户操作日志
     *
     * @param type       操作类型，如 create/update/delete
     * @param hisData    原始数据（旧数据）
     * @param entityType 实体类型，如 Product / Stash
     * @param entityId   实体 ID
     * @param updateData 更新数据（新数据）
     * @param actorName  操作人名称
     */
    public void userLogUtil(
            String type,
            Object hisData,
            String entityType,
            Integer entityId,
            Object updateData,
            String actorName) throws JsonProcessingException {

        UserLog userLog = new UserLog();
        userLog.setType(type);
        userLog.setEntityType(entityType);
        userLog.setEntityId(entityId);
        userLog.setActorName(actorName);

        // 使用统一的 ObjectMapper 进行序列化
        userLog.setUpdateData(objectMapper.writeValueAsString(updateData));
        userLog.setHisData(objectMapper.writeValueAsString(hisData));

        userLogService.insertUserLog(userLog);
    }
}