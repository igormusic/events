package com.tvmsoftware.eventslibrary.publishers.elastic;

import com.tvmsoftware.eventslibrary.model.Event;
import com.tvmsoftware.eventslibrary.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@Service
@Slf4j
public class ElasticPublisher implements EventPublisher {

    public static String ELASTIC_INDEX = "events";
    private final ReactiveElasticsearchClient client;

    public ElasticPublisher(ReactiveElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public <T extends Event> boolean canPublish(T event) {
        return true;
    }

    @Override
    public <T extends Event> void publish(T event) {

        String jsonObject = event.toJson();


        Mono<IndexResponse> response = client.index(request ->

        request.index(ELASTIC_INDEX)
                .type(event.getType())
                .id(event.getId())
                .source(jsonObject, XContentType.JSON)
                .setRefreshPolicy(IMMEDIATE));

        response
                .doOnError( ex -> log.error("Error storing in elastic", ex))
                .subscribe(indexResponse-> log.info("Event stored in elastic, response: " + indexResponse.toString()));
    }
}
