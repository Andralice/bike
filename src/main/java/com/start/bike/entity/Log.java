package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Log {
    private Integer logId; // 日志ID
    private String type;
    private String hisData; // 历史数据
    private String carrySQL ; // 执行SQL
    private String updateData; // 更新数据
    private String actorName; // 执行操作的用户
    private LocalDateTime actionTime; // 操作时间
}