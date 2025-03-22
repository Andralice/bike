package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

import java.util.List;

public interface SuppliersMapper {
    Suppliers selectSuppliersById(Integer suppliersId);
    Suppliers selectSuppliersCreate(Suppliers suppliers);
    List<Suppliers> selectAllSuppliers(int page, int size);
    List<Suppliers> selectSuppliers(Suppliers suppliers);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliersById(Integer suppliersId);
}
