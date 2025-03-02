package com.start.bike.controller;

import com.start.bike.entity.Inventory;
import com.start.bike.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/selectInventory")
    public ResponseEntity<Map<String, Object>> selectInventory(@RequestBody Inventory inventory) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 参数校验示例
            if (inventory.getInventoryId() == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            Inventory result = inventoryService.selectInventory(inventory);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "库存记录不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/insertInventory")
    public ResponseEntity<Map<String, Object>> insertInventory(@RequestBody Inventory inventory) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必填字段校验
            if (inventory.getProductId() == null) {
                body.put("success", "false");
                body.put("message", "商品ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            if (inventory.getStashId() == null) {
                body.put("success", "false");
                body.put("message", "仓库ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            if (inventory.getQuantity() == null) {
                body.put("success", "false");
            }

            inventoryService.insertInventory(inventory);
            body.put("success", "true");
            body.put("message", "库存记录创建成功");
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存创建失败，请稍后重试");
            body.put("result", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/updateInventory")
    public ResponseEntity<Map<String, Object>> updateInventory(@RequestBody Inventory inventory) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (inventory.getInventoryId() == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            inventoryService.updateInventory(inventory);
//            if (result == null) {
//                body.put("success", "false");
//                body.put("message", "库存记录不存在");
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
//            }
            body.put("success", "true");
            body.put("message", "库存更新成功");
            Inventory result = inventoryService.selectInventory(inventory);
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/deleteInventory")
    public ResponseEntity<Map<String, Object>> deleteInventory(@RequestBody Inventory inventory) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (inventory.getInventoryId() == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            boolean deleteResult = inventoryService.deleteInventory(inventory);
            if (!deleteResult) {
                body.put("success", "false");
                body.put("message", "库存记录不存在或删除失败");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "库存删除成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存删除失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}