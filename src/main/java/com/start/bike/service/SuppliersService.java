package com.start.bike.service;

import com.start.bike.entity.Suppliers;

public interface SuppliersService {
    Suppliers selectSuppliers(Suppliers suppliers);
    Suppliers insertSuppliers(Suppliers suppliers);
    Suppliers updateSuppliers(Suppliers suppliers);
    Suppliers deleteSuppliers(Suppliers suppliers);
}
