package com.start.bike.controller;

import com.start.bike.entity.Page;
import com.start.bike.entity.Suppliers;
import com.start.bike.service.SuppliersService;
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
    public ResponseEntity<Map<String, Object>> selectAllSuppliers(@RequestBody Page data) {
        Map<String, Object> body = new HashMap<>();
        int page = data.getPage();
        int size = data.getSize();
        try {
            List<Suppliers> result = suppliersService.selectAllSuppliers(page,size);
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
            Suppliers supplier = suppliersService.selectSuppliersById(suppliers.getSupplierId());
            // 必须校验ID存在
            if (supplier == null) {
                body.put("success", "false");
                body.put("message", "供应商不存在");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            suppliersService.updateSuppliers(suppliers);
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
    public ResponseEntity<Map<String, Object>> deleteSuppliers(@PathVariable Integer suppliersId) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (suppliersId == null) {
                body.put("success", "false");
                body.put("message", "供应商ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            boolean deleteResult = suppliersService.deleteSuppliersById(suppliersId);
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