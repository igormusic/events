package com.tvmsoftware.eventslibrary;

import com.tvmsoftware.eventslibrary.model.Event;

public interface EventPublisher {
   <T extends Event>  boolean canPublish(T event);
   <T extends Event> void publish(T event);
}
