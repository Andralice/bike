package com.start.bike.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.start.bike.entity.Suppliers;
import org.apache.ibatis.annotations.Param;

public interface SuppliersMapper {
    Suppliers selectSuppliers(Suppliers suppliers);
    void insertSuppliers(Suppliers suppliers);
    void updateSuppliers(Suppliers suppliers);
    int deleteSuppliers(Suppliers suppliers);

    Page<Suppliers> selectSuppliersByPage(
            Page<Suppliers> page,
            Suppliers suppliers
    );}
