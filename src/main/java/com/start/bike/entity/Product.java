package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Product {
    private Integer productId; // 商品ID
    private String productName; // 商品名称
    private String category; // 类别
    private String stashName; // 仓库名称
    private String supplierName; // 供应商名称
    private String storageTemperature; // 存储温度
    private String remark; // 备注
    private Timestamp createTime; // 创建时间
    private Timestamp updateTime; // 更新时间
}
