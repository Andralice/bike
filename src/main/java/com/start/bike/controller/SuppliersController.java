package com.start.bike.controller;

import com.start.bike.entity.Suppliers;
import com.start.bike.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Suppliers")
public class SuppliersController {

    @Autowired
    private SuppliersService suppliersService;

    @PostMapping("/selectSuppliers")
    public ResponseEntity<Map<String, Object>> selectSuppliers(@RequestBody Suppliers suppliers) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 参数校验示例
//            if (suppliers.getSupplierId() == null) {
//                body.put("success", "false");
//                body.put("message", "供应商ID不能为空");
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
//            }

            Suppliers result = suppliersService.selectSuppliers(suppliers);
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

    @PostMapping("/insertSuppliers")
    public ResponseEntity<Map<String, Object>> insertSuppliers(@RequestBody Suppliers suppliers) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必填字段校验
            if (suppliers.getSupplierName() == null || suppliers.getSupplierName().isEmpty()) {
                body.put("success", "false");
                body.put("message", "供应商名称不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            suppliersService.insertSuppliers(suppliers);
            body.put("success", "true");
            body.put("message", "供应商创建成功");
            Suppliers result = suppliersService.selectSuppliers(suppliers);
            body.put("result", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/updateSuppliers")
    public ResponseEntity<Map<String, Object>> updateSuppliers(@RequestBody Suppliers suppliers) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (suppliers.getSupplierId() == null) {
                body.put("success", "false");
                body.put("message", "供应商ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            suppliersService.updateSuppliers(suppliers);
            body.put("success", "true");
            body.put("message", "供应商更新成功");
            Suppliers result = suppliersService.selectSuppliers(suppliers);
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "供应商更新失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/deleteSuppliers")
    public ResponseEntity<Map<String, Object>> deleteSuppliers(@RequestBody Suppliers suppliers) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (suppliers.getSupplierId() == null) {
                body.put("success", "false");
                body.put("message", "供应商ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            boolean deleteResult = suppliersService.deleteSuppliers(suppliers);
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