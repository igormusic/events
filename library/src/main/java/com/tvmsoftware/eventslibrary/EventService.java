package com.tvmsoftware.eventslibrary;

import com.tvmsoftware.eventslibrary.model.Event;

public interface EventService {
    <T extends Event> T create(Class<T> eventClass, Object source);

    <T extends Event> void publish(T event);
}
