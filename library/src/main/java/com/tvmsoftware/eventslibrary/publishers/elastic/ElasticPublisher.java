package com.tvmsoftware.eventslibrary.publishers.elastic;

import com.tvmsoftware.eventslibrary.model.Event;
import com.tvmsoftware.eventslibrary.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;
import org.elasticsearch.client.Request;

import java.io.IOException;


@Service
@Slf4j
public class ElasticPublisher implements EventPublisher {

    public static String ELASTIC_INDEX = "events";
    private final RestClient client;

    public ElasticPublisher(RestClient client) {
        this.client = client;
    }

    @Override
    public <T extends Event> boolean canPublish(T event) {
        return true;
    }

    @Override
    public <T extends Event> void publish(T event) {

        Request request = new Request("POST", "events/_doc/" + event.getId());

        request.setJsonEntity(event.toJson());

        Response response = null;

        try {
            response=client.performRequest(request);

            log.info("Elastic document created, status:" + response.getStatusLine());
        } catch (IOException e) {
            log.error("Elastic Search failed", e);
        }
    }
}
