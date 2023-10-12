package org.food.delivery.dto;

import org.food.delivery.option.PaymentMethod;
import org.food.delivery.status.OrderStatus;
import org.food.delivery.status.PaymentStatus;

import java.util.Date;

public class OrderResponseDto {
    private String userID;
    private String userName;
    private String shippingAddress;
    private String orderID;
    private OrderStatus orderStatus;
    private Date orderDate;
    private String productID;
    private String paymentID;
    private Long amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Date paymentDate;
}
