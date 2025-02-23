package com.start.bike.service;

import com.start.bike.entity.Inventory;

public interface InventoryService {
    Inventory selectInventory(Inventory inventory);
    Inventory insertInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    Boolean deleteInventory(Inventory inventory);
}
