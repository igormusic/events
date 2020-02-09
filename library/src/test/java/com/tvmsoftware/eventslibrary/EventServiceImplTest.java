package com.tvmsoftware.eventslibrary;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.read.ListAppender;
import com.tvmsoftware.eventslibrary.model.Event;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@SpringBootTest
class EventServiceImplTest {

    @Autowired
    EventService service;

    @Autowired
    ApplicationCreatedListener listener;

    @Mock
    private Appender<ILoggingEvent> mockAppender;

    @Test
    void createBasicEvent() {
        Event event = service.create(Event.class, this);

        assertThat(event.getType(), is(Event.class.getName()));

    }
    @Test
    void createApplicationEvent() {
        ApplicationCreatedEvent event = service.create(ApplicationCreatedEvent.class, this);

        event.setApplicationType(ApplicationType.COMMERCIAL);
        event.setNumberOfApplicants(3);
        event.setResourceId("application:983232");
        event.setUserId("IdentityId:" + UUID.randomUUID().toString());
        event.setCorrelationId(UUID.randomUUID().toString());

        assertThat(event.getType(), is(ApplicationCreatedEvent.class.getName()));

        Logger fooLogger = (Logger) LoggerFactory.getLogger("events");
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        fooLogger.addAppender(listAppender);

        service.publish(event);

        // it was logged
        List<ILoggingEvent> logsList = listAppender.list;
        assertThat(logsList.get(0).getMessage(), is(not(emptyString())));
        assertThat(logsList.get(0).getLevel().levelStr, is(Level.INFO.levelStr));

        //spring event was published
        assertThat(listener.getLastEvent().getId(), is(event.getId()));
    }

}