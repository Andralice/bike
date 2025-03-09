package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Task {
    public Integer taskId;
    public String taskName;
    public String taskDescription;
    public String taskStatus;
    public String taskType;
    public List<String> taskUserName;
    public String taskAdminName;
    public Timestamp createTime;
    public Timestamp updateTime;
    public Timestamp endTime;
}
