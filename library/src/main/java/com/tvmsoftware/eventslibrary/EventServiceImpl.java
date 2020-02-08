package com.tvmsoftware.eventslibrary;

import com.tvmsoftware.eventslibrary.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

@Service
@Slf4j
public class EventServiceImpl implements EventService {
  private final ApplicationContext applicationContext;

  public EventServiceImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public <T extends Event> T create(Class<T> eventClass) {
    T event = null;
    try {
      event = eventClass.newInstance();
    } catch (Exception e) {
      log.error("Error creating event type " + eventClass.getName(), e);
    }

    if(event == null){
      return null;
    }

    event.setSource(applicationContext.getId());
    event.setType(eventClass.getTypeName());
    event.setUserId(getUser());

    return event;
  }

  @Override
  public <T extends Event> void publish(T event){
    Map<String, EventPublisher> publishers = applicationContext.getBeansOfType(EventPublisher.class);

    for(EventPublisher publisher: publishers.values()){

      if(publisher.canPublish(event)){
        publisher.publish(event);
      }
    }
  }

  private String getUser() {
    if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
      return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    return null;
  }
}