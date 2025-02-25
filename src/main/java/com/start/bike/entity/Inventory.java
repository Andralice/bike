package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Inventory {
    private Integer inventoryId;
    private Integer productId;
    private Integer stashId;
    private Integer quantity;
    private Timestamp createTime;          // 创建时间
    private Timestamp updateTime;          // 更新时间
}
