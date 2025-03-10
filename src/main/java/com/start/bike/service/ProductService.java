package com.start.bike.service;

import com.start.bike.entity.Product;

import java.util.List;

public interface ProductService {
    Product selectProductById(Integer productId);
    List<Product> selectProductByName(Product product);
    Product selectProductCreate(Product product);
    List<Product> selectAllProduct(int page, int size);
    void insertProduct(Product product);
    void updateProduct(Product product);
    Boolean deleteProductById(Integer productId);
}
