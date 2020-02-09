package com.tvmsoftware.eventslibrary.publishers.elastic;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

import java.time.Duration;

@Configuration
class ElasticConfiguration {
    @Bean
    RestHighLevelClient client() {

        ClientConfiguration clientConfiguration = getClientConfiguration();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    RestClient lowLevelClient() {

        ClientConfiguration clientConfiguration = getClientConfiguration();

        return RestClients.create(clientConfiguration).lowLevelRest();
    }

    private ClientConfiguration getClientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .build();
    }
}
