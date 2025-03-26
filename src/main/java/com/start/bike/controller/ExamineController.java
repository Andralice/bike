package com.start.bike.controller;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.*;
import com.start.bike.service.ExamineService;
import com.start.bike.service.InventoryService;
import com.start.bike.service.ProductService;
import com.start.bike.service.UserService;
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
    private UserService userService;

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
    public ResponseEntity<Map<String, Object>> UpdateExamine(@RequestBody Examine examine){
        Map<String, Object> body = new HashMap<>();
        try {
            examineService.UpdateExamine(examine);
            body.put("success", "true");
            body.put("message", "审核成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "审核失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }



}
