package com.start.bike.service;

import com.start.bike.entity.Inventory;

public interface InventoryService {
    Inventory selectInventory(Inventory inventory);
    void insertInventory(Inventory inventory);
    void updateInventory(Inventory inventory);
    Boolean deleteInventory(Inventory inventory);
}
