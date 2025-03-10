package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Log {
    private Integer logId; // 日志ID
    private String action; // 操作类型
    private String description; // 操作描述
    private String actor; // 执行操作的用户
    private Timestamp actionTime; // 操作时间
    private String details; // 详细信息
}