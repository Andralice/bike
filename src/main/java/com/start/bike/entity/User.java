package com.start.bike.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("users")
public class User {

    private String username; // 用户名
    private String password; // 密码
    private String createTime; // 创建时间
    private String phone; // 电话号码
    private String position; // 职位
    private String responsibleWarehouse; // 负责仓库

    // Getter 和 Setter 方法
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getResponsibleWarehouse() {
        return responsibleWarehouse;
    }

    public void setResponsibleWarehouse(String responsibleWarehouse) {
        this.responsibleWarehouse = responsibleWarehouse;
    }
}