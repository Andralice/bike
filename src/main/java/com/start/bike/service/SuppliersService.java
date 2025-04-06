package com.start.bike.service;

import com.start.bike.entity.Suppliers;

import java.util.List;

public interface SuppliersService {
    Suppliers selectSuppliersById(Integer suppliersId);
    Suppliers selectSuppliersCreate(Suppliers suppliers);
    List<Suppliers> selectAllSuppliers(Suppliers suppliers);
    List<Suppliers> selectAllSuppliers();
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    Boolean deleteSuppliersById(Integer suppliersId);
}
