package org.food.delivery.config.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.food.delivery.entity.User;
import org.food.delivery.event.Event;
import org.food.delivery.service.impl.UserEventType;


import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class UserEvent implements Event {
    private UUID eventId = UUID.randomUUID();
    private Date eventDate  = new Date();
    private User user;
    private UserEventType eventType;
    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return eventDate;
    }

    public UserEvent(User user, UserEventType eventType) {
        this.user = user;
        this.eventType = eventType;
    }
}
