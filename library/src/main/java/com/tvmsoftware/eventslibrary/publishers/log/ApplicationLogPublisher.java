package com.tvmsoftware.eventslibrary.publishers.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvmsoftware.eventslibrary.model.Event;
import com.tvmsoftware.eventslibrary.EventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationLogPublisher implements EventPublisher {

    public static final String LOG_TOPIC = "events";

    @Override
    public <T extends Event> boolean canPublish(T event) {
        return true;
    }

    @Override
    public <T extends Event> void publish(T event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        Logger log = LoggerFactory.getLogger(LOG_TOPIC);

        try {
            log.info(mapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            log.error("error logging event " + event.toString(), e);
        }

    }
}
