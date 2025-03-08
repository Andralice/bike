package com.start.bike.mapper;
import com.start.bike.entity.Suppliers;

import java.util.List;

public interface SuppliersMapper {
    Suppliers selectSuppliersById(Integer suppliersId);
    List<Suppliers> selectAllSuppliers(int page, int size);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliersById(Integer suppliersId);
}
