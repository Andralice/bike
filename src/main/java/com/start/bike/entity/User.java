package com.start.bike.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // 忽略 null 和 空字符串
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
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}



