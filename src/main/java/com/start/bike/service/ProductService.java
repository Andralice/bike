package com.start.bike.service;

import com.start.bike.entity.Product;

public interface ProductService {
    Product selectProduct(Product product);
    void insertProduct(Product product);
    void updateProduct(Product product);
    Boolean deleteProduct(Product product);
}
