package com.start.bike.mapper;
import com.start.bike.entity.Product;

import java.util.List;

public interface ProductMapper {
    Product selectProductById(Integer productId);
    List<Product> selectAllProduct();
    Product selectProductCreate(Product product);
    List<Product> selectAllProduct(Product product);
    void insertProduct(Product product);
    void updateProduct(Product product);
    int deleteProductById(Integer productId);
}
