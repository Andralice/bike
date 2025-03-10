package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer userId; // 用户ID
    private String username; // 用户名
    private String adminName; // 管理员名称
    private String password; // 密码
    private String position; // 职位
    private String telephone; // 电话
    private String workPlace; // 工作地点
    private String role; // 角色
    private String remark; // 备注
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
}



