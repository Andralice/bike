package com.start.bike.service;

import com.start.bike.entity.Product;

import java.util.List;

public interface ProductService {
    Product selectProductById(Integer productId);
    Product selectProductCreate(Product product);
    List<Product> selectAllProduct(Product product);
    List<Product> selectAllProduct();
    void insertProduct(Product product);
    void updateProduct(Product product);
    Boolean deleteProductById(Integer productId);
}
