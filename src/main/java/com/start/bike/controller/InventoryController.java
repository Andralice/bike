package com.start.bike.controller;
import com.start.bike.entity.Inventory;
import com.start.bike.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    @RequestMapping("/selectInventory")
    public ResponseEntity<?> selectInventory(@RequestBody Inventory inventory)
    {
        return ResponseEntity.ok(inventoryService.selectInventory(inventory));
    }

    @RequestMapping("/insertInventory")
    public ResponseEntity<?> insertInventory(@RequestBody Inventory inventory)
    {
        return ResponseEntity.ok(inventoryService.insertInventory(inventory));
    }

    @RequestMapping("/updateInventory")
    public ResponseEntity<?> updateInventory(@RequestBody Inventory inventory)
    {
        return ResponseEntity.ok(inventoryService.updateInventory(inventory));
    }

    @RequestMapping("/deleteInventory")
    public ResponseEntity<?> deleteInventory(@RequestBody Inventory inventory)
    {
        return ResponseEntity.ok(inventoryService.deleteInventory(inventory));
    }
}
