package com.start.bike.entity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Suppliers {
    private Integer supplierId; // 供应商ID（主键，自增）
    private String supplierName; // 供应商名称（唯一）
    private String contactName; // 联系人姓名
    private String contactPhone; // 联系电话
    private String address; // 详细地址
    private String bankAccount; // 银行账号
    private Boolean cooperationStatus; // 合作状态（true-停用，false-启用），默认值为true
    private Integer num; // 查询数量
    private Integer page; // 查询页数
    private String remark;// 备注
    private Timestamp createTime; // 创建时间，默认值为CURRENT_TIMESTAMP
    private Timestamp updateTime; // 更新时间，默认值为CURRENT_TIMESTAMP，并在更新时自动更新
}
