package org.food.delivery.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.dto.PaymentRequestDto;
import org.food.delivery.status.PaymentStatus;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PaymentEvent implements Event{
    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();

    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }


    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }
}
