package com.start.bike.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
@RequestMapping("/Examine")
public class ExamineController {
    @Autowired
    private ExamineService examineService;
    @Autowired
    private UserRole userRole;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private LogUtil logUtil;
    @RequestMapping("/selectExamineById/{examineId}")
    public ResponseEntity<Map<String, Object>> selectExamineById(
            @PathVariable Integer examineId) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 参数校验示例
            if (examineId == null) {
                body.put("success", "false");
                body.put("message", "库存ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            Examine result = examineService.selectExamineById(examineId);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "审核记录不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/selectAllExamine")
    public ResponseEntity<Map<String, Object>> selectAllExamine(@RequestBody Page data ){
        Map<String, Object> body = new HashMap<>();
        int page = data.getPage();
        int size = data.getSize();
        try {
            List<Examine> result = examineService.selectAllExamine(page, size);
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/selectListExamine")
    public ResponseEntity<Map<String, Object>> selectListExamine(@RequestBody Examine examine){
        Map<String, Object> body = new HashMap<>();
        try {
            List<Examine> result = examineService.selectListExamine(examine);
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/UpdateExamine")
    public ResponseEntity<Map<String, Object>> UpdateExamine(
            @RequestBody Examine examine,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser){
        Map<String, Object> body = new HashMap<>();
        try {
            examineService.UpdateExamine(examine);
            if (Objects.equals(examine.getExamineType(), "createInventory")){
                ObjectMapper objectMapper = new ObjectMapper();
                Inventory inventory = objectMapper.readValue(examine.getExamineData(), Inventory.class);
                Inventory updateData = inventoryService.selectInventoryCreate(inventory);
                inventoryService.insertInventory(inventory);
                // 获取最后执行的 SQL 语句
                String executedSql = ThreadLocalContext.getLastExecutedSql();
                // 记录操作日志
                logUtil.logOperation("createInventory",'0', executedSql, updateData, operatorUser);

                examine.setExamineStatus("1"); // 0-待审核 1-审核通过 2-审核不通过

                examineService.UpdateExamine(examine);
                // 获取最后执行的 SQL 语句
                String examine_executedSql = ThreadLocalContext.getLastExecutedSql();
                // 记录操作日志
                logUtil.logOperation("updateExamine",examine.getExamineType(), examine_executedSql, examine, operatorUser);

                body.put("success", "true");
                body.put("message", "审核通过");
                return ResponseEntity.ok(body);
            } else if (Objects.equals(examine.getExamineType(), "updateInventory")) { // 假设这里是更新库存的类型
                Map<String, Object> responseBody = new HashMap<>();
                try {
                    // 必须校验ID存在
                    ObjectMapper objectMapper = new ObjectMapper();
                    Inventory inventory = objectMapper.readValue(examine.getExamineData(), Inventory.class);
                    Inventory hisData = inventoryService.selectInventoryCreate(inventory);
                    if (hisData.getInventoryId() == null) {
                        responseBody.put("success", "false");
                        responseBody.put("message", "库存ID不能为空");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
                    }
                    Inventory updateData = inventoryService.selectInventoryCreate(inventory);
                    int num = inventory.getQuantity();

                    if ("add".equals(inventory.getType())) {
                        updateData.setQuantity(hisData.getQuantity() + num);
                    } else {
                        updateData.setQuantity(hisData.getQuantity() - num);
                    }

                    inventoryService.updateInventory(updateData);

                    // 获取最后执行的 SQL 语句
                    String executedSql = ThreadLocalContext.getLastExecutedSql();

                    // 记录操作日志
                    logUtil.logOperation("updateInventory", hisData, executedSql, updateData, operatorUser);
                    responseBody.put("success", "true");
                    responseBody.put("message", "库存更新成功");
                    responseBody.put("result", updateData);
                    return ResponseEntity.ok(responseBody);
                } catch (Exception e) {
                    responseBody.put("success", "false");
                    responseBody.put("message", "库存更新失败，请稍后重试");
                    responseBody.put("result", e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
                }
            }
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "审核失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
        return ResponseEntity.badRequest().build();
    }
}
