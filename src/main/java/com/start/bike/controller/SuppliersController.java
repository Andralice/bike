package com.start.bike.controller;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.Page;
import com.start.bike.entity.Suppliers;
import com.start.bike.service.SuppliersService;
import com.start.bike.util.LogUtil;
import com.start.bike.util.UserLogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersService suppliersService;

    @Autowired
    private LogUtil logUtil;

    @Autowired
    private UserLogUtil userLogUtil;

    @PostMapping("/selectSuppliersById/{suppliersId}")
    public ResponseEntity<Map<String, Object>> selectSuppliers(@PathVariable Integer suppliersId) {
        Map<String, Object> body = new HashMap<>();
        try {
            Suppliers result = suppliersService.selectSuppliersById(suppliersId);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "供应商不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商查询失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/selectAllSuppliers")
    public ResponseEntity<Map<String, Object>> selectAllSuppliers(@RequestBody Suppliers suppliers) {
        Map<String, Object> body = new HashMap<>();
        try {
            List<Suppliers> result;
            if (suppliers == null) {
                result = suppliersService.selectAllSuppliers();
            }else {
                result = suppliersService.selectAllSuppliers(suppliers);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e){
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/createSuppliers")
    public ResponseEntity<Map<String, Object>> insertSuppliers(
            @RequestBody Suppliers suppliers,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必填字段校验
            if (suppliers.getSupplierName() == null || suppliers.getSupplierName().isEmpty()) {
                body.put("success", "false");
                body.put("message", "供应商名称不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            suppliersService.insertSuppliers(suppliers);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();

            Suppliers updateData = suppliersService.selectSuppliersCreate(suppliers);

            // 记录操作日志
            logUtil.logOperation("createSuppliers","0", executedSql, updateData, operatorUser);
            userLogUtil.userLogUtil(
                    "create",
                    0,
                    "Suppliers",
                    updateData.getSupplierId(),
                    updateData,
                    operatorUser);


            body.put("success", "true");
            body.put("message", "供应商创建成功");
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateSuppliers")
    public ResponseEntity<Map<String, Object>> updateSuppliers(
            @RequestBody Suppliers suppliers,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            Suppliers hisData = suppliersService.selectSuppliersById(suppliers.getSupplierId());

            suppliersService.updateSuppliers(suppliers);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();

            Suppliers updateData = suppliersService.selectSuppliersById(suppliers.getSupplierId());

            // 记录操作日志
            logUtil.logOperation("updateSuppliers",hisData, executedSql, updateData, operatorUser);
            userLogUtil.userLogUtil(
                    "delete",
                    hisData,
                    "Suppliers",
                    updateData.getSupplierId(),
                    updateData,
                    operatorUser);


            body.put("success", "true");
            body.put("message", "供应商更新成功");
            Suppliers result = suppliersService.selectSuppliersById(suppliers.getSupplierId());
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商更新失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/deleteSuppliersById/{suppliersId}")
    public ResponseEntity<Map<String, Object>> deleteSuppliers(
            @PathVariable Integer suppliersId,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            Suppliers hisData = suppliersService.selectSuppliersById(suppliersId);

            boolean deleteResult = suppliersService.deleteSuppliersById(suppliersId);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 记录操作日志
            logUtil.logOperation("delSuppliers",hisData, executedSql, "0", operatorUser);
            userLogUtil.userLogUtil(
                    "delete",
                    hisData,
                    "Suppliers",
                    suppliersId,
                    0,
                    operatorUser);


            if (!deleteResult) {
                body.put("success", "false");
                body.put("message", "供应商不存在或删除失败");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "供应商删除成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商删除失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}