package com.start.bike.mapper;
import com.start.bike.entity.Product;

public interface ProductMapper {
    Product selectProduct(Product product);
    void insertProduct(Product product);
    void updateProduct(Product product);
    int deleteProduct(Product product);
}
