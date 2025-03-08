package com.start.bike.service;

import com.start.bike.entity.Suppliers;
import com.start.bike.mapper.SuppliersMapper;

import java.util.List;

public interface SuppliersService {
    Suppliers selectSuppliersById(Integer suppliersId);
    List<Suppliers> selectAllSuppliers(int page, int size);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    Boolean deleteSuppliersById(Integer suppliersId);
}
