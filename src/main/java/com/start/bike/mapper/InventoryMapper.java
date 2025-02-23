package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

public interface InventoryMapper {
    Inventory selectInventory(Inventory inventory);
    Inventory insertInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    int deleteInventory(Inventory inventory);
}
