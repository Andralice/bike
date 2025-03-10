package com.start.bike.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Inventory {
    private Integer inventoryId; // 库存ID
    private String productName; // 商品名称
    private String stashName; // 仓库名称
    private String supplierName; // 供应商名称
    private String type; // 类型
    private String remark; // 备注
    private Integer quantity; // 数量
    private LocalDateTime lastStockTime; // 最后入库时间
    private Byte isDeleted; // 是否删除（0-未删除，1-已删除）
}
