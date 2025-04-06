package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

import java.util.List;

public interface SuppliersMapper {
    Suppliers selectSuppliersById(Integer suppliersId);
    Suppliers selectSuppliersCreate(Suppliers suppliers);
    List<Suppliers> selectAllSuppliers(Suppliers suppliers);
    List<Suppliers> selectAllSuppliers();
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliersById(Integer suppliersId);
}
