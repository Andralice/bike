package com.start.bike.service;

import com.start.bike.entity.Suppliers;

public interface SuppliersService {
    Suppliers selectSuppliersById(Integer suppliersId);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    Boolean deleteSuppliersById(Integer suppliersId);
}
