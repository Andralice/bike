package com.start.bike.controller;
import com.start.bike.entity.Product;
import com.start.bike.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/selectProduct")
    public ResponseEntity<?> selectProduct(@RequestBody Product product)
    {
        Product product1 = productService.selectProduct(product);
        return ResponseEntity.ok(product1);
    }
    @RequestMapping("/insertProduct")
    public ResponseEntity<?> insertProduct(@RequestBody Product product)
    {
        Product product1 = productService.insertProduct(product);
        return ResponseEntity.ok(product1);
    }
    @RequestMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody Product product)
    {
        Product product1 = productService.updateProduct(product);
        return ResponseEntity.ok(product1);
    }
    @RequestMapping("/deleteProduct")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product)
    {
        Product product1 = productService.deleteProduct(product);
        return ResponseEntity.ok(product1);
    }
}
