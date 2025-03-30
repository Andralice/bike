package com.start.bike.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY) // 忽略 null 和 空字符串
public class Suppliers {
    private Integer supplierId; // 供应商ID
    private String supplierName; // 供应商名称
    private String contactName; // 联系人姓名
    private String contactPhone; // 联系电话
    private String address; // 地址
    private String bankAccount; // 银行账户
    private String remark; // 备注
    private int cooperationStatus; // 合作状态
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}

