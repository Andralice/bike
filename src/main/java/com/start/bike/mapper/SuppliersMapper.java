package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

public interface SuppliersMapper {
    Suppliers selectSuppliersById(Integer suppliersId);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliersById(Integer suppliersId);
}
