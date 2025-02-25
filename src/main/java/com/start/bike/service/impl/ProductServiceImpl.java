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
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
    @Override
    public Boolean deleteProduct(Product product) {
        int result = productMapper.deleteProduct(product);
        return result > 0;
    }


}
