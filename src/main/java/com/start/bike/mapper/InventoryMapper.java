package com.start.bike.mapper;
import com.start.bike.entity.Inventory;

import java.util.List;

public interface InventoryMapper {
    Inventory selectInventory(Inventory inventory);
    List<Inventory> selectAllInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    int deleteInventory(Inventory inventory);
}
