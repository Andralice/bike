package com.start.bike.service.impl;

import com.start.bike.service.ProductService;
import com.start.bike.entity.Product;
import com.start.bike.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public Product selectProductById(Integer productId) {
        return productMapper.selectProductById(productId);
    }

    @Override
    public List<Product> selectProductByName(Product product) {
        return productMapper.selectProduct(product);
    }
    @Override
    public Product selectProductCreate(Product product) {
        return productMapper.selectProductCreate(product);
    }

    @Override
    public List<Product> selectAllProduct(int page, int Size) {
        return productMapper.selectAllProduct(page, Size);
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
    public Boolean deleteProductById(Integer productId) {
        int result = productMapper.deleteProductById(productId);
        return result > 0;
    }


}
