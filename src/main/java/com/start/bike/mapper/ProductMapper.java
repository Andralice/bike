package com.start.bike.mapper;
import com.start.bike.entity.Product;

import java.util.List;

public interface ProductMapper {
    Product selectProductById(Integer productId);
    List<Product> selectProductByName(Product product);
    Product selectProductCreate(Product product);
    List<Product> selectAllProduct(int page, int size);
    void insertProduct(Product product);
    void updateProduct(Product product);
    int deleteProductById(Integer productId);
}
