package com.tvmsoftware.eventslibrary.publishers.kafka;

import com.tvmsoftware.eventslibrary.EventPublisher;
import com.tvmsoftware.eventslibrary.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaEventPublisher implements EventPublisher {

    private final Processor processor;

    public KafkaEventPublisher(Processor processor) {
        this.processor = processor;
    }

    @Override
    public <T extends Event> boolean canPublish(T event) {
        //publish all
        return true;
    }

    @Override
    public <T extends Event> void publish(T event) {

        Message<T> message = MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.MESSAGE_KEY, event.getId())
                .build();

        boolean result = processor.output()
                .send(message);

        log.info("published Kafka event message with result:" + result);
    }
}
