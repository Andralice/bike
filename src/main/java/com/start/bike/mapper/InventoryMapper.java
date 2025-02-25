package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

public interface InventoryMapper {
    Inventory selectInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    int deleteInventory(Inventory inventory);
}
