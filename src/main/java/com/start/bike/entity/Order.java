package com.start.bike.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Order {
    private Integer orderId; // 订单ID
    private String orderNumber; // 订单编号
    private Integer customerId; // 客户ID
    private Timestamp orderDate; // 下单日期
    private Timestamp deliveryDate; // 发货日期
    private String status; // 订单状态
    private BigDecimal totalAmount; // 订单总金额
    private String remark; // 备注
}
