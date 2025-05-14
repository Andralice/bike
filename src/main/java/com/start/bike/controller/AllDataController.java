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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<Map<String, Object>> allData() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> stash = new HashMap<>();
        Map<String, Object> product = new HashMap<>();
        Map<String, Object> inventory = new HashMap<>();

        // 获取仓库、商品、库存及库存日志列表
        List<Stash> stashList = stashService.selectAllStash();
        List<Product> productList = productService.selectAllProduct();
        List<Inventory> inventoryList = inventoryService.selectAllInventory();
        List<Inventory> inventoryLog = inventoryService.selectAllInventoryLog();

        // 计算不同商品名称的数量
        int distinctProductNameLength = (int) productList.stream()
                .map(Product::getProductName)
                .distinct()
                .count();

        // 初始化用于存储特定筛选条件下的商品列表
        List<Map<String, Object>> noInventoryProducts = new ArrayList<>();
        List<Map<String, Object>> noTimeProducts = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 创建一个映射来存储每个产品的库存信息
        Map<String, List<Inventory>> inventoryMap = new HashMap<>();
        for (Inventory invItem : inventoryList) {
            inventoryMap.computeIfAbsent(invItem.getProductName(), k -> new ArrayList<>()).add(invItem);
        }

        // 遍历productList，检查每个产品的库存情况
        for (Product item : productList) {
            // 清空图片链接减少传输数据量
            item.setImageUrl(null);

            List<Inventory> inventories = inventoryMap.getOrDefault(item.getProductName(), Collections.emptyList());
            boolean hasNonZeroInventory = false;
            String stashName = null;
            String supplierName = null;

            // 标志是否有正库存
            boolean hasPositiveInventory = false;

// 遍历所有库存项
            for (Inventory invItem : inventories) {
                if (invItem.getQuantity() > 0) {
                    hasPositiveInventory = true;
                    break; // 只要有一个正库存就跳出循环
                }

                // 当前库存为0或负数，添加为一条“无库存产品”
                Map<String, Object> noProduct = new HashMap<>();
                noProduct.put("productName", item.getProductName());

                stashName = invItem.getStashName();
                noProduct.put("stashName", stashName != null ? stashName : "未知仓库");

                supplierName = invItem.getSupplierName();
                noProduct.put("supplierName", supplierName != null ? supplierName : "未知供应商");

                noInventoryProducts.add(noProduct); // 添加一条记录
            }


// 如果没有正库存，说明所有都是无库存商品（已全部添加）
            // 如果没有非零库存，则加入无库存产品列表
//            if (!hasNonZeroInventory) {
//                Map<String, Object> noProduct = new HashMap<>();
//                noProduct.put("productName", item.getProductName());
//                if(stashName!=null){
//                    noProduct.put("stashName", stashName);
//                }else {
//                    noProduct.put("stashName", "未知仓库");
//                }
//                if(supplierName!=null){
//                    noProduct.put("supplierName", supplierName);
//                }else {
//                    noProduct.put("supplierName", "未知供应商");
//                }
//                noInventoryProducts.add(noProduct);
//            } else {
                // 检查即将过期的商品
                for (Inventory inventoryItem : inventories) {
                    LocalDate productionDate = LocalDate.parse(inventoryItem.getProductionDate(), formatter);
                    long shelfLife = item.getShelfLife(); // 从商品中获取保质期

                    // 计算剩余天数
                    long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), productionDate.plusDays(shelfLife));

                    // 如果剩余天数小于30天，则认为是即将过期商品
                    if (daysLeft <= 30 && daysLeft >= 0) {
                        Map<String, Object> noTimeProduct = new HashMap<>();
                        noTimeProduct.put("productName", item.getProductName());
                        noTimeProduct.put("stashName", inventoryItem.getStashName());
                        noTimeProduct.put("supplierName", inventoryItem.getSupplierName());
                        noTimeProduct.put("productionDate", inventoryItem.getProductionDate());
                        noTimeProduct.put("shelfLife", shelfLife);
                        noTimeProduct.put("daysLeft", daysLeft);
                        noTimeProducts.add(noTimeProduct);
                    }
                }
            }
//        }

        // 处理没有库存记录的商品
        // for (Product item : productList) {
        //     if (!inventoryMap.containsKey(item.getProductName())) {
        //         // 创建一个用于查询库存信息的对象
        //         Inventory inventoryQuery = new Inventory();
        //         inventoryQuery.setProductName(item.getProductName());

        //         // 查询库存信息
        //         Inventory productInventory = inventoryService.selectInventoryCreate(inventoryQuery);

        //         // 如果没有库存或库存为0，则加入无库存产品列表
        //         if (productInventory == null || productInventory.getQuantity() == 0) {
        //             Map<String, Object> noProduct = new HashMap<>();
        //             noProduct.put("productName", item.getProductName());
        //             noProduct.put("stashName", null); // 添加仓库名称
        //             noProduct.put("supplierName", null); // 添加供应商名称
        //             noInventoryProducts.add(noProduct);
        //         }
        //     }
        // }

        // 统计库存总量、增加和减少的数量
        int addNum = 0;
        int subNum = 0;
        int totalNum = 0;

        for (Inventory item : inventoryList) {
            totalNum += item.getQuantity();
        }

        for (Inventory item : inventoryLog) {
            if ("add".equals(item.getType())) {
                addNum += item.getQuantity();
            } else if ("sub".equals(item.getType())) {
                subNum += item.getQuantity();
            }
        }
        // 处理没有库存记录的商品（即未入库的商品）
        for (Product item : productList) {
            String productName = item.getProductName();

            // 如果该商品不在 inventoryMap 中，说明从未入库
            if (!inventoryMap.containsKey(productName)) {
                Map<String, Object> noProduct = new HashMap<>();
                noProduct.put("productName", productName);
                noProduct.put("stashName", "未知仓库");
                noProduct.put("supplierName", "未知供应商");
                noInventoryProducts.add(noProduct);
            }
        }

        // 将数据封装进Map中准备返回
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

        map.put("noTimeProducts", noTimeProducts);
        map.put("noTimeNum", noTimeProducts.size());

        return ResponseEntity.ok(map);
    }
}



