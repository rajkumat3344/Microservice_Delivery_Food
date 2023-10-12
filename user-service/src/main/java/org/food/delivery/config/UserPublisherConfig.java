package org.food.delivery.config;

import org.food.delivery.config.event.UserEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;


@Service
public class UserPublisherConfig {

    @Bean
    public Sinks.Many<UserEvent> userSinks(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<UserEvent>> userEventSupplier(Sinks.Many<UserEvent> sinks){
        return sinks::asFlux;
    }
}
