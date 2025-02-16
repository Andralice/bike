package com.start.bike.controller;
import com.start.bike.entity.Suppliers;
import com.start.bike.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/Suppliers")
public class SuppliersController {
    @Autowired
    private SuppliersService suppliersService;
    @PostMapping("/selectSuppliers")
    public ResponseEntity<?> selectSuppliers(@RequestBody Suppliers suppliers){
        try {
            suppliersService.selectSuppliers(suppliers);
            return ResponseEntity.badRequest().body("selectSuppliers successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/insertSuppliers")
    public ResponseEntity<?> insertSuppliers(@RequestBody Suppliers suppliers){
        try {
            suppliersService.insertSuppliers(suppliers);
            return ResponseEntity.badRequest().body("insertSuppliers successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/updateSuppliers")
    public ResponseEntity<?> updateSuppliers(@RequestBody Suppliers suppliers){
        try {
            suppliersService.updateSuppliers(suppliers);
            return ResponseEntity.badRequest().body("updateSuppliers successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/deleteSuppliers")
    public ResponseEntity<?> deleteSuppliers(@RequestBody Suppliers suppliers){
        try {
            suppliersService.deleteSuppliers(suppliers);
            return ResponseEntity.badRequest().body("deleteSuppliers successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
