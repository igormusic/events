package com.tvmsoftware.eventslibrary;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import com.tvmsoftware.eventslibrary.model.Event;
import org.junit.jupiter.api.Test;

class EventTest {
    @Test
    public void create_schema_for_custom_event(){
        String avroSchema = Event.generateAvroSchema(ApplicationCreatedEvent.class);

        assertThat(avroSchema, is(not(emptyString())));
    }
}