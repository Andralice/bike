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
    public Inventory insertInventory(Inventory inventory) {
        return inventoryMapper.insertInventory(inventory);
    }

    @Override
    public Inventory updateInventory(Inventory inventory) {
        return inventoryMapper.updateInventory(inventory);
    }

    @Override
    public Inventory deleteInventory(Inventory inventory) {
        return inventoryMapper.deleteInventory(inventory);
    }

}
