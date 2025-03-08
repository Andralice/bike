package com.start.bike.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.bike.entity.Suppliers;

public interface SuppliersService {
    Suppliers selectSuppliers(Suppliers suppliers);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    Boolean deleteSuppliers(Suppliers suppliers);

    Page<Suppliers> getSuppliersByPage(int pageNum, int pageSize, Suppliers suppliers);
}
