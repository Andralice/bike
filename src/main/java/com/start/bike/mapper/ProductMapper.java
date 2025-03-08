package com.start.bike.mapper;
import com.start.bike.entity.Product;

public interface ProductMapper {
    Product selectProductById(Integer productId);
    void insertProduct(Product product);
    void updateProduct(Product product);
    int deleteProductById(Integer productId);
}
