package com.start.bike.controller;


import com.start.bike.entity.Inventory;
import com.start.bike.entity.Product;
import com.start.bike.entity.Stash;
import com.start.bike.service.InventoryService;
import com.start.bike.service.ProductService;
import com.start.bike.service.StashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/Panel")
public class AllDataController {
    @Autowired
    private StashService stashService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/allData")
    public ResponseEntity<Map<String, Object>> allData(){
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> stash = new HashMap<>();
        Map<String, Object> product = new HashMap<>();
        Map<String, Object> inventory = new HashMap<>();

        List<Stash> stashList = stashService.selectAllStash();
        List<Product> productList = productService.selectAllProduct();
        List<Inventory> inventoryList = inventoryService.selectAllInventory();
        List<Inventory> inventoryLog = inventoryService.selectAllInventoryLog();
        // 获取所有不重复的productName长度
        int distinctProductNameLength = (int) productList.stream()
                .map(Product::getProductName)
                .distinct()
                .count();


        List<Map> noInventoryProducts = new ArrayList<>();
        // 遍历产品列表中的每个Product对象
        for(Product item : productList) {
            // 将当前Product对象的imageUrl字段设置为null，实现清空操作
            item.setImageUrl(null);

            // 创建一个新的Inventory对象，用于查询库存信息
            Inventory inventory_new = new Inventory();
            // 设置Inventory对象的productName属性，与当前Product对象的名称相同
            inventory_new.setProductName(item.getProductName());
            // 设置Inventory对象的stashName属性，与当前Product对象的储藏室名称相同
            inventory_new.setStashName(item.getStashName());
            // 设置Inventory对象的supplierName属性，与当前Product对象的供应商名称相同
            inventory_new.setSupplierName(item.getSupplierName());

            // 调用inventoryService的selectInventoryCreate方法，传入Inventory对象，查询库存信息
            Inventory Product_Inventory = inventoryService.selectInventoryCreate(inventory_new);
            List<Inventory> Product_Inventory_Log = inventoryService.selectAllInventoryLog();
            item.setAddNum(0);
            item.setSubNum(0);
            for (Inventory inventory1 : Product_Inventory_Log) {
                if (Objects.equals(item.getProductName(),
                        inventory1.getProductName()) && Objects.equals(item.getStashName(),
                        inventory1.getStashName()) && Objects.equals(item.getSupplierName(),
                        inventory1.getSupplierName())){
                    if (Objects.equals(inventory1.getType(), "add")) {
                        item.setAddNum(item.getAddNum() + inventory1.getQuantity());
                    }else {
                        item.setSubNum(item.getSubNum() + inventory1.getQuantity());
                    }
                }
            }

            if(Product_Inventory.getQuantity() == 0) {
                // 将没有库存的商品名称添加到数组中
                Map<String, Object> noProduct = new HashMap<>();
                noProduct.put("productName", item.getProductName());
                noProduct.put("stashName", item.getStashName());
                noProduct.put("supplierName", item.getSupplierName());
                noInventoryProducts.add(noProduct);
            }else {
                // 如果查询结果不为空，说明当前Product对象有库存信息，将quantity字段设置为库存数量
                item.setQuantity(Product_Inventory.getQuantity());
            }
        }

        // 库存
        int addNum = 0;
        int subNum = 0;
        int totalNum = 0;
        for(Inventory item : inventoryList) {
            totalNum += item.getQuantity();
        }

        for (Inventory item : inventoryLog) {

            if (Objects.equals(item.getType(), "add")) {
                addNum += item.getQuantity();
            } else if (Objects.equals(item.getType(), "sub")) {
                subNum += item.getQuantity();
            }
        }

        stash.put("stashList", stashList);
        stash.put("num", stashList.size());
        product.put("productList", productList);
        product.put("num", productList.size());
        product.put("ProductNum", distinctProductNameLength);
        inventory.put("inventoryList", inventoryList);
        inventory.put("num", inventoryList.size());
        inventory.put("addNum", addNum);
        inventory.put("subNum", subNum);
        inventory.put("totalNum", totalNum);


        map.put("stash", stash);
        map.put("product", product);
        map.put("inventory", inventory);
        map.put("noInventoryProducts", noInventoryProducts);
        map.put("noInventoryNum", noInventoryProducts.size());

        return ResponseEntity.ok(map);
    }
}