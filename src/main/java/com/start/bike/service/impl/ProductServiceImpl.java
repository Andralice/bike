package com.start.bike.service.impl;

import com.start.bike.service.ProductService;
import com.start.bike.entity.Product;
import com.start.bike.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Product selectProduct(Product product) {
        return productMapper.selectProduct(product);
    }
    @Override
    public Product insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }
    @Override
    public Product updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }
    @Override
    public Product deleteProduct(Product product) {
        return productMapper.deleteProduct(product);
    }


}
