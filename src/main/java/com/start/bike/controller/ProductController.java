package com.start.bike.controller;

import com.start.bike.entity.Product;
import com.start.bike.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/selectProduct")
    public ResponseEntity<Map<String, Object>> selectProduct(@RequestBody Product product) {
        Map<String, Object> body = new HashMap<>();
        try {
            Product result = productService.selectProduct(product);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "商品不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "查询成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "商品查询失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/insertProduct")
    public ResponseEntity<Map<String, Object>> insertProduct(@RequestBody Product product) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 基础参数校验示例
            if (product.getProductName() == null || product.getProductName().isEmpty()) {
                body.put("success", "false");
                body.put("message", "商品名称不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            Product result = productService.insertProduct(product);
            body.put("success", "true");
            body.put("message", "商品创建成功");
            body.put("result", result);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "商品创建失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/updateProduct")
    public ResponseEntity<Map<String, Object>> updateProduct(@RequestBody Product product) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (product.getProductId() == null) {
                body.put("success", "false");
                body.put("message", "商品ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            Product result = productService.updateProduct(product);
            if (result == null) {
                body.put("success", "false");
                body.put("message", "商品不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "商品更新成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "商品更新失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/deleteProduct")
    public ResponseEntity<Map<String, Object>> deleteProduct(@RequestBody Product product) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (product.getProductId() == null) {
                body.put("success", "false");
                body.put("message", "商品ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            boolean deleteResult = productService.deleteProduct(product);
            if (!deleteResult) {
                body.put("success", "false");
                body.put("message", "商品不存在或删除失败");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
            }
            body.put("success", "true");
            body.put("message", "商品删除成功");
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "商品删除失败，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }
}