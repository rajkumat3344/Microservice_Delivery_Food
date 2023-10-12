package org.food.delivery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.option.PaymentMethod;
import org.food.delivery.status.OrderStatus;
import org.food.delivery.status.PaymentStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ORDER_TBL")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String purchaseID;
    private Date purchaseDate;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus purchaseStatus;
    private String userId;
    private String username;
    private String shippingAddress;

    //TODO: Product Details
    private String productID;
    //TODO: PaymentID, PaymentStatus and Payment Method
    private String paymentID;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Long amount;
    private Date paymentDate;
}
