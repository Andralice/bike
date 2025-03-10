package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Suppliers {
    private Integer supplierId; // 供应商ID
    private String supplierName; // 供应商名称
    private String contactName; // 联系人姓名
    private String contactPhone; // 联系电话
    private String address; // 地址
    private String bankAccount; // 银行账户
    private String remark; // 备注
    private Byte cooperationStatus; // 合作状态
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}

