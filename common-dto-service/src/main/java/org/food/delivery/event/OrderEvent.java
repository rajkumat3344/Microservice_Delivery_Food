package org.food.delivery.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.dto.OrderRequestDto;
import org.food.delivery.status.OrderStatus;

import java.util.Date;
import java.util.UUID;
@NoArgsConstructor
@Data
public class OrderEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date eventDate  = new Date();
    private OrderRequestDto requestDto;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }


    public OrderEvent(OrderRequestDto requestDto, OrderStatus orderStatus) {
        this.requestDto = requestDto;
        this.orderStatus = orderStatus;
    }
}
