package com.start.bike.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // 忽略 null 和 空字符串
public class UserLog {
    private Integer userLogId; // 日志ID
    private String type;
    private String entityType ; // 种类
    private Integer entityId; // 种类ID
    private String hisData; // 历史数据
    private String updateData; // 更新数据
    private String actorName; // 执行操作的用户
    private LocalDateTime actionTime; // 操作时间
}