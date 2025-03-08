package com.start.bike.service.impl;

import com.start.bike.entity.Inventory;
import com.start.bike.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.start.bike.mapper.InventoryMapper;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public Inventory selectInventoryById(Integer inventoryId) {
        return inventoryMapper.selectInventoryById(inventoryId);
    }

    @Override
    public List<Inventory> selectAllInventory(int page, int size) {
        return inventoryMapper.selectAllInventory(page, size);
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
    public Boolean deleteInventoryById(Integer inventoryId) {
        int result = inventoryMapper.deleteInventoryById(inventoryId);
        return result > 0;
    }
}
