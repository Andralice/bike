package com.start.bike.service.impl;
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
    public Suppliers selectSuppliers(Suppliers suppliers)
    {
        return suppliersMapper.selectSuppliers(suppliers);
    }
    @Override
    public Suppliers insertSuppliers(Suppliers suppliers)
    {
        return suppliersMapper.insertSuppliers(suppliers);
    }
    @Override
    public Suppliers updateSuppliers(Suppliers suppliers)
    {
        return suppliersMapper.updateSuppliers(suppliers);
    }
    @Override
    public Boolean deleteSuppliers(Suppliers suppliers) {
        int result = suppliersMapper.deleteSuppliers(suppliers);
        return result > 0;
    }
}
