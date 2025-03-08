package com.start.bike.service.impl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.bike.entity.Suppliers;
import com.start.bike.service.SuppliersService;
import com.start.bike.mapper.SuppliersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SuppliersServiceImpl implements SuppliersService {
    @Autowired
    private SuppliersMapper suppliersMapper;
    @Override
    public Suppliers selectSuppliersById(Integer suppliersId)
    {
        return suppliersMapper.selectSuppliersById(suppliersId);
    }
    @Override
    public void insertSuppliers(Suppliers suppliers)
    {
        suppliersMapper.insertSuppliers(suppliers);
    }
    @Override
    public void updateSuppliers(Suppliers suppliers)
    {
        suppliersMapper.updateSuppliers(suppliers);
    }
    @Override
    public Boolean deleteSuppliersById(Integer suppliersId) {
        int result = suppliersMapper.deleteSuppliersById(suppliersId);
        return result > 0;
    }

    @Override
    public Page<Suppliers> getSuppliersByPage(int pageNum, int pageSize, Suppliers suppliers) {
        Page<Suppliers> page = new Page<>(pageNum, pageSize);
        return suppliersMapper.selectSuppliersByPage(page,suppliers);
    };
    }



