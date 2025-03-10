package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Task {
    private Integer taskId; // 任务ID
    private String taskName; // 任务名称
    private String taskDescription; // 任务描述
    private String taskStatus; // 任务状态
    private String taskType; // 任务类型
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
    private Timestamp endTime; // 结束时间
    private String taskAdminName; // 任务管理员名称
    private String taskUserNames; // 执行人员JSON数组
}
