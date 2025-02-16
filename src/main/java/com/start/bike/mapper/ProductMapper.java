package com.start.bike.mapper;
import com.start.bike.entity.Product;

public interface ProductMapper {
    Product selectProduct(Product product);
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Product product);
}
