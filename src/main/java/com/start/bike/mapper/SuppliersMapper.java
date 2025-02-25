package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

public interface SuppliersMapper {
    Suppliers selectSuppliers(Suppliers suppliers);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliers(Suppliers suppliers);
}
