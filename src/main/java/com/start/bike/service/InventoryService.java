package com.start.bike.service;

import com.start.bike.entity.Inventory;
import com.start.bike.mapper.InventoryMapper;

import java.util.List;

public interface InventoryService {
    Inventory selectInventoryById(Integer inventoryId);
    List<Inventory> selectAllInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Boolean deleteInventoryById(Integer inventoryId);
}
