package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Stash {
    private Integer stashId; // 仓库ID
    private String stashName; // 仓库名称
    private String stashAddress; // 仓库地址
    private String storageTemperature; // 存储温度
    private String stashArea; // 仓库面积
    private String adminName; // 管理员名称
    private String remark; // 备注
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private Byte isDeleted; // 是否删除（0-未删除，1-已删除）
}



