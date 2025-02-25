package com.start.bike.service.impl;

import com.start.bike.entity.Inventory;
import com.start.bike.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.start.bike.mapper.InventoryMapper;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public Inventory selectInventory(Inventory inventory) {
        return inventoryMapper.selectInventory(inventory);
    }

    @Override
    public void insertInventory(Inventory inventory) {
        inventoryMapper.insertInventory(inventory);
    }

    @Override
    public void updateInventory(Inventory inventory) {
        inventoryMapper.updateInventory(inventory);
    }

    @Override
    public Boolean deleteInventory(Inventory inventory) {
        int result = inventoryMapper.deleteInventory(inventory);
        return result > 0;
    }
}
