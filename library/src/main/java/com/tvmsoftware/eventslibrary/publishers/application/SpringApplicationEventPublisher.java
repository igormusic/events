package com.tvmsoftware.eventslibrary.publishers.application;

import com.tvmsoftware.eventslibrary.EventPublisher;
import com.tvmsoftware.eventslibrary.model.Event;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class SpringApplicationEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public SpringApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public <T extends Event> boolean canPublish(T event) {
        return true;
    }

    @Override
    public <T extends Event> void publish(T event) {
        eventPublisher.publishEvent(event);
    }
}
