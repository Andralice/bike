package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Product {
    private Integer productId;        // 商品ID（主键，自增）
    private String productName;       // 商品名称（唯一）
    private String category;          // 商品类别（如食品、日用品）
    private Integer supplierId;       // 供应商ID（外键关联供应商表）
    private Timestamp createTime;          // 创建时间
    private Timestamp updateTime;          // 更新时间
}
