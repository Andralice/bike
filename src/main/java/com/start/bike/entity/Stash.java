package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Stash {

    private Integer stashId; // 仓库编号
    private String stashName; // 仓库名称
    private String stashAddress; // 仓库地址
    private String storageTemperature; // 存储温度
    private String stashArea; // 仓库面积
    private String managerName;// 管理员名称
    private Integer num; // 查询数量
    private Integer page; // 查询页数
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
}



