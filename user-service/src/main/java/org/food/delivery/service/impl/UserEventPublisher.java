package org.food.delivery.service.impl;

import org.food.delivery.config.event.UserEvent;
import org.food.delivery.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class UserEventPublisher {

    private final Sinks.Many<UserEvent> userSinks;

    @Autowired
    public UserEventPublisher(Sinks.Many<UserEvent> userSinks) {
        this.userSinks = userSinks;
    }

    public void publishUserEvent(User user, UserEventType eventType) {
        UserEvent userEvent = new UserEvent(user, eventType.USER_CREATED);
        userSinks.tryEmitNext(userEvent);
    }
}
