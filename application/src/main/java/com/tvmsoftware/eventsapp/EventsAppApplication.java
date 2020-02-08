package com.tvmsoftware.eventsapp;

import com.tvmsoftware.eventslibrary.EventService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventsAppApplication {
	final EventService service;

	public EventsAppApplication(EventService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(EventsAppApplication.class, args);
	}

}
