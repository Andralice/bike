package com.start.bike.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer orderId; // 订单ID
    private String orderNumber; // 订单编号
    private Integer customerId; // 客户ID
    private LocalDateTime orderDate; // 下单日期
    private LocalDateTime deliveryDate; // 发货日期
    private String status; // 订单状态
    private BigDecimal totalAmount; // 订单总金额
    private String remark; // 备注
}
