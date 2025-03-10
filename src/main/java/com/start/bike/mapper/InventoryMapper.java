package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryMapper {
    Inventory selectInventoryById(Integer inventoryId);
    Inventory selectInventoryCreate(Inventory inventory);
    List<Inventory> selectAllInventory(int page, int size);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    int deleteInventoryById(Integer inventoryId);
}
