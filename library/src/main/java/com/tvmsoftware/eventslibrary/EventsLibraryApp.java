package com.tvmsoftware.eventslibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableTransactionManagement
@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
public class EventsLibraryApp {

    public static void main(String[] args) {
        SpringApplication.run(EventsLibraryApp.class, args);
    }

}