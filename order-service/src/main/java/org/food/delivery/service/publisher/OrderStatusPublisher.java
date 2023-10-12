package org.food.delivery.service.publisher;

import org.food.delivery.dto.OrderRequestDto;
import org.food.delivery.event.OrderEvent;
import org.food.delivery.status.OrderStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDto requestDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(requestDto, orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
