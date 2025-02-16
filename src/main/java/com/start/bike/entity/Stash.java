package com.start.bike.entity;
import java.sql.Timestamp;

public class Stash {
    public Integer getStashId() {
        return stashId;
    }

    public void setStashId(Integer stashId) {
        this.stashId = stashId;
    }

    public String getStashName() {
        return stashName;
    }

    public void setStashName(String stashName) {
        this.stashName = stashName;
    }

    public String getStashAddress() {
        return stashAddress;
    }

    public void setStashAddress(String stashAddress) {
        this.stashAddress = stashAddress;
    }

    public String getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(String storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public String getStashArea() {
        return stashArea;
    }

    public void setStashArea(String stashArea) {
        this.stashArea = stashArea;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    private Integer stashId; // 仓库编号
    private String stashName; // 仓库名称
    private String stashAddress; // 仓库地址
    private String storageTemperature; // 存储温度
    private String stashArea; // 仓库面积
    private Integer managerId; // 管理员编号
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
}



