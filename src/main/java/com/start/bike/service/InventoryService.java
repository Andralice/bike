package com.start.bike.service;

import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory selectInventoryById(Integer inventoryId);
    List<Inventory> selectInventoryCreatety(Inventory inventory);
    Inventory selectInventoryCreate(Inventory inventory);
    List<Inventory> selectAllInventory(Inventory inventory);
    List<Inventory> selectAllInventory();
    List<Inventory> selectAllInventoryLog();
    List<Inventory> selectAllInventoryLog(Inventory inventory);
    void insertInventory(Inventory inventory);
    void insertInventoryLog(Inventory inventory);
    void updateInventory(Inventory inventory);
    Boolean deleteInventoryById(Integer inventoryId);
}
