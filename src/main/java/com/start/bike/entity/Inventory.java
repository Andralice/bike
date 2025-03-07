package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Inventory {
    private Integer inventoryId;
    private Integer productId;  // 商品ID
    private Integer stashId;    // 仓库ID
    private Integer supplierId; // 供应商ID
    private Integer quantity; // 剩余库存
    private Integer num; // 查询数量
    private Integer page; // 查询页数
    private Timestamp lastStockTime;
}
