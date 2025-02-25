package com.start.bike.service;

import com.start.bike.entity.Suppliers;

public interface SuppliersService {
    Suppliers selectSuppliers(Suppliers suppliers);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    Boolean deleteSuppliers(Suppliers suppliers);
}
