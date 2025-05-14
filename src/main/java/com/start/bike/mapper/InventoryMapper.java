package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryMapper {
    Inventory selectInventoryById(Integer inventoryId);
    Inventory selectInventoryCreate(Inventory inventory);
    List<Inventory> selectInventoryCreatety(Inventory inventory);
    List<Inventory> selectAllInventory(Inventory inventory);
    List<Inventory> selectAllInventory();
    List<Inventory> selectAllInventoryLog();
    List<Inventory> selectAllInventoryLog(Inventory inventory);
    void insertInventory(Inventory inventory);
    void insertInventoryLog(Inventory inventory);
    void updateInventory(Inventory inventory);
    int deleteInventoryById(Integer inventoryId);
}
