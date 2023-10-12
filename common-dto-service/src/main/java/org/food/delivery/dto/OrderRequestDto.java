package org.food.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.option.PaymentMethod;
import org.food.delivery.status.OrderStatus;
import org.food.delivery.status.PaymentStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderRequestDto {
    private String orderID;
    private String userID;
    private String userName;
    private String shippingAddress;
    private OrderStatus orderStatus;
    private Date orderDate;
    private String productID;
    private String paymentID;
    private Long amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Date paymentDate;


}
