package com.start.bike.entity;
import java.sql.Timestamp;


public class Suppliers {
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Integer getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(Integer cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    private Integer supplierId; // 供应商ID（主键，自增）
    private String supplierName; // 供应商名称（唯一）
    private String contactName; // 联系人姓名
    private String contactPhone; // 联系电话
    private String address; // 详细地址
    private String bankAccount; // 银行账号
    private Integer cooperationStatus; // 合作状态（0-停用，1-启用），默认值为1
    private Timestamp createTime; // 创建时间，默认值为CURRENT_TIMESTAMP
    private Timestamp updateTime; // 更新时间，默认值为CURRENT_TIMESTAMP，并在更新时自动更新
}
