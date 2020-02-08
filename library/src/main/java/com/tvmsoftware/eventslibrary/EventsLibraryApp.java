package com.tvmsoftware.eventslibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EventsLibraryApp {

    public static void main(String[] args) {
        SpringApplication.run(EventsLibraryApp.class, args);
    }

}