package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Inventory {
    private Integer inventoryId;
    private String productName;//  商品名称
    private String stashName; // 仓库名称
    private String supplierName; // 供应商名称
    private String type;
    private Integer quantity; // 剩余库存
    private String remark;// 备注
    private Integer num; // 查询数量
    private Integer page; // 查询页数
    private Timestamp lastStockTime;
}
