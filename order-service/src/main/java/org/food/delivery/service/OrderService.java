package org.food.delivery.service;

import org.food.delivery.dto.OrderRequestDto;
import org.food.delivery.entity.PurchaseOrder;

import java.util.List;

public interface OrderService {
     PurchaseOrder initiateOrder(OrderRequestDto requestDto);

     List<PurchaseOrder> getOrders();
}
