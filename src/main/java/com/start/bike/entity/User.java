package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer userId; // 用户ID，主键
    private String username; // 用户名
    private String password; // 密码
    private String position; // 职位或角色描述
    private String telephone; // 电话号码
    private String workPlace; // 工作地点
    private String role; // 角色（如：管理员、普通用户等）
    private Integer num; // 查询数量
    private Integer page; // 查询页数
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
}



