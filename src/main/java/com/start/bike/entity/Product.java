package com.start.bike.entity;
import java.sql.Timestamp;


public class Product {
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMinStock() {
        return minStock;
    }

    public void setMinStock(Integer minStock) {
        this.minStock = minStock;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    private Integer productId;        // 商品ID（主键，自增）
    private String productName;       // 商品名称（唯一）
    private String category;          // 商品类别（如食品、日用品）
    private Integer supplierId;       // 供应商ID（外键关联供应商表）
    private Integer minStock;         // 最低库存预警
    private Timestamp createTime;          // 创建时间
    private Timestamp updateTime;          // 更新时间
}
