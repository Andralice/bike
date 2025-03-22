package com.start.bike.controller;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.Inventory;
import com.start.bike.entity.Page;
import com.start.bike.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.start.bike.util.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private LogUtil logUtil;

    @RequestMapping("/selectInventoryById/{inventoryId}")
    public ResponseEntity<Map<String, Object>> selectInventory(
            @PathVariable Integer inventoryId) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 参数校验示例
            if (inventoryId == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            Inventory result = inventoryService.selectInventoryById(inventoryId);
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

    @RequestMapping("/selectAllInventory")
    public ResponseEntity<Map<String, Object>> selectAllInventory(@RequestBody Page data) {
        Map<String, Object> body = new HashMap<>();
        int page = data.getPage();
        int size = data.getSize();
        try {
            List<Inventory> result = inventoryService.selectAllInventory(page, size);
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库查询失败，请稍后重试");
            body.put("error",e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/createInventory")
    public ResponseEntity<Map<String, Object>> insertInventory(
            @RequestBody Inventory inventory,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必填字段校验
            if (inventory.getProductName() == null) {
                body.put("success", "false");
                body.put("message", "商品ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            if (inventory.getStashName() == null) {
                body.put("success", "false");
                body.put("message", "仓库ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            if (inventory.getQuantity() == null) {
                body.put("success", "false");
            }
            inventoryService.insertInventory(inventory);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 获取创建数据
            Inventory updateData = inventoryService.selectInventoryCreate(inventory);
            // 记录操作日志
            logUtil.logOperation("createInventory","0", executedSql, updateData, operatorUser);

            body.put("success", "true");
            body.put("message", "库存记录创建成功");
            body.put("result",updateData);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/updateInventory")
    public ResponseEntity<Map<String, Object>> updateInventory(
            @RequestBody Inventory inventory,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (inventory.getInventoryId() == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }
            Inventory hisData = inventoryService.selectInventoryById(inventory.getInventoryId());
            inventoryService.updateInventory(inventory);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            Inventory updateData = inventoryService.selectInventoryById(inventory.getInventoryId());
            // 记录操作日志
            logUtil.logOperation("updateInventory",hisData, executedSql, updateData, operatorUser);

            body.put("success", "true");
            body.put("message", "库存更新成功");
            body.put("result", updateData);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/deleteInventoryById/{inventoryId}")
    public ResponseEntity<Map<String, Object>> deleteInventoryById(
            @PathVariable Integer inventoryId,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {

            Inventory hisData = inventoryService.selectInventoryById(inventoryId);

            boolean deleteResult = inventoryService.deleteInventoryById(inventoryId);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 记录操作日志
            logUtil.logOperation("delInventory",hisData, executedSql, "0", operatorUser);

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