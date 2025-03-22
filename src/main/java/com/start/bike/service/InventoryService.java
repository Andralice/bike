package com.start.bike.service;

import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory selectInventoryById(Integer inventoryId);
    Inventory selectInventoryCreate(Inventory inventory);
    List<Inventory> selectAllInventory(int page, int size);
    List<Inventory> selectInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Boolean deleteInventoryById(Integer inventoryId);
}
