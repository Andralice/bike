package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryMapper {
    Inventory selectInventoryById(Integer inventoryId);
    List<Inventory> selectAllInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    int deleteInventoryById(Integer inventoryId);
}
