package com.tvmsoftware.eventslibrary;

import lombok.Getter;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApplicationCreatedListener implements ApplicationListener<ApplicationCreatedEvent> {

    protected ApplicationCreatedEvent lastEvent;

    @Override
    public void onApplicationEvent(ApplicationCreatedEvent applicationCreatedEvent) {
        this.lastEvent = applicationCreatedEvent;
    }
}
