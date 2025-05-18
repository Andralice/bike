package com.start.bike.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.start.bike.entity.*;
import com.start.bike.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/UserLog")
public class UserLogController {
    @Autowired
    private UserLogService userLogService;

    @PostMapping("/selectUserLog")
    public ResponseEntity<Map<String, Object>> selectUserLog(@RequestBody UserLog userLog){
        Map<String, Object> body = new HashMap<>();
        try {
            List<UserLog> result = userLogService.selectUserLog(userLog.getEntityId(),userLog.getEntityType());
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

    @PostMapping("/ComparisonData")
    public ResponseEntity<Map<String, Object>> insertUserLog(@RequestBody UserLog log){
        Map<String, Object> body = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        try {
            UserLog userLog =   userLogService.selectUserLogById(log.getUserLogId());
            // 获取实体类型名称
            String entityTypeStr = userLog.getEntityType();
            Class<?> entityType = null;

            // 根据类型名称映射到具体类
            switch (entityTypeStr) {
                case "Inventory":
                    entityType = Inventory.class;
                    break;
                case "Product":
                    entityType = Product.class;
                    break;
                case "Stash":
                    entityType = Stash.class;
                    break;
                case "Suppliers":
                    entityType = Suppliers.class;
                    break;
                default:
                    throw new IllegalArgumentException("不支持的实体类型: " + entityTypeStr);
            }
            Map<String, Object> comparisonData = new HashMap<>();
            if (Objects.equals(userLog.getType(), "create")){
                Object obj = objectMapper.readValue(userLog.getUpdateData(), entityType);
                comparisonData.put("HisData", null);
                comparisonData.put("UpdateData", obj);
            }else if (Objects.equals(userLog.getType(), "update")){
                Object obj = objectMapper.readValue(userLog.getHisData(), entityType);
                Object obj2 = objectMapper.readValue(userLog.getUpdateData(), entityType);
                comparisonData.put("HisData", obj);
                comparisonData.put("UpdateData", obj2);
            }else if (Objects.equals(userLog.getType(), "delete")){
                Object obj = objectMapper.readValue(userLog.getHisData(), entityType);
                comparisonData.put("HisData", obj);
                comparisonData.put("UpdateData", null);
            }

            body.put("comparisonData", comparisonData);
            body.put("success", "true");
            body.put("message", "添加成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "添加失败，请稍后重试");
            body.put("error",e.getMessage() );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

}
