package com.start.bike.entity;

import java.sql.Timestamp;

public class Inventory {
    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStashId() {
        return stashId;
    }

    public void setStashId(Integer stashId) {
        this.stashId = stashId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLastStockTime() {
        return lastStockTime;
    }

    public void setLastStockTime(Timestamp lastStockTime) {
        this.lastStockTime = lastStockTime;
    }

    private Integer inventoryId;
    private Integer productId;
    private Integer stashId;
    private Integer quantity;
    private Timestamp lastStockTime;
}
