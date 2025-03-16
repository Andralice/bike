package com.start.bike.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class Product {
    private Integer productId; // 商品ID
    private String productName; // 商品名称
    private String category; // 类别
    private String stashName; // 仓库名称
    private String supplierName; // 供应商名称
    private String storageTemperature; // 存储温度
    private String remark; // 备注
    private int shelfLife; // 保质期
    private String  imageUrl; // 商品图片
    private String productTime; // 生产日期
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
