package com.start.bike.service;

import com.start.bike.entity.Product;

public interface ProductService {
    Product selectProduct(Product product);
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    Boolean deleteProduct(Product product);
}
