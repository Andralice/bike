package com.start.bike.controller;

import com.start.bike.context.ThreadLocalContext;
import com.start.bike.entity.Page;
import com.start.bike.entity.Product;
import com.start.bike.service.ProductService;
import com.start.bike.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private LogUtil logUtil;


    @RequestMapping("/selectProductById/{productId}")
    public ResponseEntity<Map<String, Object>> selectProduct(@PathVariable Integer productId) {
        Map<String, Object> body = new HashMap<>();
        try {
            Product result = productService.selectProductById(productId);
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

    @RequestMapping("/selectAllProduct")
    public ResponseEntity<Map<String, Object>> selectAllProduct(@RequestBody Page data) {
        Map<String, Object> body = new HashMap<>();
        int page = data.getPage();
        int size = data.getSize();
        try {
            List<Product> result = productService.selectAllProduct(page, size);
            body.put("success", "true");
            body.put("result", result);
            return ResponseEntity.ok(body);
        }catch (Exception e) {
            body.put("success", "false");
            body.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Map<String, Object>> insertProduct(
            @RequestBody Product product,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 基础参数校验示例
            if (product.getProductName() == null || product.getProductName().isEmpty()) {
                body.put("success", false);
                body.put("message", "商品名称不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            productService.insertProduct(product);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 获取创建数据
            Product updateData = productService.selectProductCreate(product);
            // 记录操作日志
            logUtil.logOperation("createProduct","0", executedSql, updateData, operatorUser);


            body.put("success", true);
            body.put("message", "商品创建成功");
            body.put("result", updateData);
            return ResponseEntity.status(HttpStatus.CREATED).body(body);
        } catch (Exception e) {
            body.put("success", false);
            body.put("message", "商品创建失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/updateProduct")
    public ResponseEntity<Map<String, Object>> updateProduct(
            @RequestBody Product product,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            // 必须校验ID存在
            if (product.getProductId() == null) {
                body.put("success", "false");
                body.put("message", "商品ID不能为空");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
            }

            // 更新前数据
            Product hisData = productService.selectProductById(product.getProductId());
            productService.updateProduct(product);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            Product result = productService.selectProductById(product.getProductId());
            // 记录操作日志
            logUtil.logOperation("updateProduct",hisData, executedSql, result, operatorUser);


            body.put("success", "true");
            body.put("message", "商品更新成功");
            body.put("result", result);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.put("success", "false");
            body.put("message", "商品更新失败，请稍后重试");
            body.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
        }
    }

    @RequestMapping("/deleteProductById/{productId}")
    public ResponseEntity<Map<String, Object>> deleteProduct(
            @PathVariable Integer productId,
            @RequestHeader(name = "X-Operator-User", required = false) String operatorUser) {
        Map<String, Object> body = new HashMap<>();
        try {
            Product hisData = productService.selectProductById(productId);
            boolean deleteResult = productService.deleteProductById(productId);
            // 获取最后执行的 SQL 语句
            String executedSql = ThreadLocalContext.getLastExecutedSql();
            // 记录操作日志
            logUtil.logOperation("delProduct", hisData, executedSql,"0", operatorUser);
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
