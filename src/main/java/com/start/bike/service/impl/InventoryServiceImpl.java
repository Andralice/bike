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
    public Inventory selectInventoryCreate(Inventory inventory) {
        return inventoryMapper.selectInventoryCreate(inventory);
    }
    @Override
    public List<Inventory> selectInventoryCreatety(Inventory inventory) {
        return inventoryMapper.selectInventoryCreatety(inventory);
    }


    @Override
    public List<Inventory> selectAllInventory(Inventory inventory) {
        return inventoryMapper.selectAllInventory(inventory);
    }

    @Override
    public List<Inventory> selectAllInventory() {
        return inventoryMapper.selectAllInventory();
    }

    @Override
    public List<Inventory> selectAllInventoryLog() {
        return inventoryMapper.selectAllInventoryLog();
    }

    @Override
    public List<Inventory> selectAllInventoryLog(Inventory inventory) {
        return inventoryMapper.selectAllInventoryLog(inventory);
    }

    @Override
    public void insertInventory(Inventory inventory) {
        inventoryMapper.insertInventory(inventory);
    }

    @Override
    public void insertInventoryLog(Inventory inventory) {
        inventoryMapper.insertInventoryLog(inventory);
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
