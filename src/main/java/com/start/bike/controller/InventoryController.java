package com.start.bike.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.*;
import com.start.bike.service.ExamineService;
import com.start.bike.service.InventoryService;
import com.start.bike.service.ProductService;
import com.start.bike.service.UserService;
import com.start.bike.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.start.bike.util.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserRole userRole;

    @Autowired
    private ExamineService examineService;

    @Autowired
    private ProductService productService;

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
    public ResponseEntity<Map<String, Object>> selectAllInventory(
            @RequestBody Inventory inventory,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            List<Inventory> result;
            if (inventory == null) {
                result = inventoryService.selectAllInventory();
            }else {
                result = inventoryService.selectAllInventory(inventory);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            body.put("num",result.size());
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "仓库查询失败，请稍后重试");
            body.put("error",e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/selectAllInventoryLog")
    public ResponseEntity<Map<String, Object>> selectAllInventoryLog(
            @RequestBody Inventory inventory,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {

            List<Inventory> result;
            if (inventory == null) {
                result = inventoryService.selectAllInventoryLog();
            }else {
                result = inventoryService.selectAllInventoryLog(inventory);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            body.put("num",result.size());
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
        ObjectMapper objectMapper = new ObjectMapper(); // 使用 Jackson
        try {
            // 必填字段校验


            inventoryService.insertInventoryLog(inventory);

            // 检查是否存在现有库存记录
            Inventory hisData = inventoryService.selectInventoryCreate(inventory);
            boolean isAdmin = Objects.equals(userRole.user_role(operatorUser), "Admin");

            if (hisData != null) {
                int num = inventory.getQuantity();
                Inventory updateData = inventoryService.selectInventoryCreate(inventory);
                if ("add".equals(inventory.getType())) {
                    updateData.setQuantity(hisData.getQuantity() + num);
                } else {
                    updateData.setQuantity(hisData.getQuantity() - num);
                }

                if (isAdmin) {
                    inventoryService.updateInventory(updateData);

                    // 获取最后执行的 SQL 语句
                    String executedSql = ThreadLocalContext.getLastExecutedSql();

                    // 记录操作日志
                    logUtil.logOperation("updateInventory", hisData, executedSql, updateData, operatorUser);

                    body.put("success", "true");
                    body.put("message", "库存记录更新成功");
                    return ResponseEntity.ok(body);
                } else {
                    // 创建审核记录
                    Examine examine = createExamine(inventory, inventory.getType(), operatorUser, objectMapper);

                    body.put("success", "true");
                    body.put("message", "库存审核已发起");
                    body.put("result", examine);
                    return ResponseEntity.ok(body);
                }
            } else {
                if (isAdmin) {
                    inventoryService.insertInventory(inventory);
                    // 获取最后执行的 SQL 语句
                    String executedSql = ThreadLocalContext.getLastExecutedSql();

                    // 记录操作日志
                    logUtil.logOperation("createInventory", "0", executedSql, inventory, operatorUser);

                    body.put("success", "true");
                    body.put("message", "库存记录创建成功");
                    return ResponseEntity.ok(body);
                } else {
                    // 创建审核记录
                    Examine examine = createExamine(inventory, inventory.getType(), operatorUser, objectMapper);

                    body.put("success", "true");
                    body.put("message", "库存审核已发起");
                    body.put("result", examine);
                    return ResponseEntity.ok(body);
                }
            }
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
            int num = inventory.getQuantity();

            if ("add".equals(inventory.getType())) {
                inventory.setQuantity(hisData.getQuantity() + num);
            } else {
                inventory.setQuantity(hisData.getQuantity() - num);
            }

            inventoryService.updateInventory(inventory);

            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            Inventory updateData = inventoryService.selectInventoryById(inventory.getInventoryId());

            // 记录操作日志
            logUtil.logOperation("updateInventory", hisData, executedSql, updateData, operatorUser);
            body.put("success", "true");
            body.put("message", "库存更新成功");
            body.put("result", updateData);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "库存更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
        return null;
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

    // 提取公共方法：创建审核记录
    private Examine createExamine(Inventory inventory, String examineType, String operatorUser, ObjectMapper objectMapper) throws JsonProcessingException, JsonProcessingException {
        Examine examine = new Examine();
        examine.setExamineName("库存" + ("createInventory".equals(examineType) ? "创建" : "更新"));

        examine.setExamineData(objectMapper.writeValueAsString(inventory)); // 使用 Jackson 序列化
        examine.setExamineType(examineType);
        examine.setExamineStatus("0"); // 0-待审核

        examineService.CreateExamine(examine);

        // 获取最后执行的 SQL 语句
        String executedSql = ThreadLocalContext.getLastExecutedSql();

        // 记录操作日志
        logUtil.logOperation("createExamine", "0", executedSql, examine, operatorUser);

        return examine;
    }

}