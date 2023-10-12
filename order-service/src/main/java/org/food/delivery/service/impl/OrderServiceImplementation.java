package org.food.delivery.service.impl;

import org.food.delivery.dto.OrderRequestDto;
import org.food.delivery.entity.Product;
import org.food.delivery.entity.PurchaseOrder;
import org.food.delivery.repository.OrderRepository;
import org.food.delivery.repository.ProductRepository;
import org.food.delivery.service.OrderService;
import org.food.delivery.service.publisher.OrderStatusPublisher;
import org.food.delivery.status.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderServiceImplementation implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderStatusPublisher orderStatusPublisher;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository,
                                      ProductRepository productRepository,
                                      OrderStatusPublisher orderStatusPublisher) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderStatusPublisher = orderStatusPublisher;
    }

    @Override
    public PurchaseOrder initiateOrder(OrderRequestDto requestDto) {
        //Check if productID is present in the ProductRepository then proceed order
        if(productRepository.findByProductID(requestDto.getProductID())){

//            //TODO: product kafka event with status ORDER_CREATED
//            orderStatusPublisher.publishOrderEvent(requestDto, OrderStatus.ORDER_CREATED);
//            return order;
        }
        return null;
        //else cancel the order with a custom exception
    }

    @Override
    public List<PurchaseOrder> getOrders() {
        return null;
    }

    //TODO: OrderRequestDto to PurchaseOrder Entity convert

}
