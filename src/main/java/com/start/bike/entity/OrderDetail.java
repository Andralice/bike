package com.start.bike.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetail {
    private Integer orderDetailId; // 订单详情ID
    private Integer orderId; // 订单ID
    private Integer productId; // 商品ID
    private Integer quantity; // 购买数量
    private BigDecimal price; // 单价
    private String remark; // 备注
}
