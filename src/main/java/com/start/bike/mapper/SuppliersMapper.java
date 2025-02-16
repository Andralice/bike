package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

public interface SuppliersMapper {
    Suppliers selectSuppliers(Suppliers suppliers);
    Suppliers insertSuppliers(Suppliers suppliers);
    Suppliers updateSuppliers(Suppliers suppliers);
    Suppliers deleteSuppliers(Suppliers suppliers);
}
