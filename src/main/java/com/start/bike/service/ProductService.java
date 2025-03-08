package com.start.bike.service;

import com.start.bike.entity.Product;

public interface ProductService {
    Product selectProductById(Integer productId);
    void insertProduct(Product product);
    void updateProduct(Product product);
    Boolean deleteProductById(Integer productId);
}
