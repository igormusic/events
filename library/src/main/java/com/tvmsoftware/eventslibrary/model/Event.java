package com.tvmsoftware.eventslibrary.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Slf4j
@ToString
public class Event {
    @JsonProperty(required = true)
    protected String id;
    @JsonProperty(required = true)
    protected String source;
    @JsonProperty(required = true)
    protected String type;
    @JsonProperty(required = true)
    protected String resourceId;
    @JsonProperty(required = true)
    protected String time;
    protected String userId;
    protected String correlationId;

    public Event(){
        this.id = UUID.randomUUID().toString();
        this.time = LocalDateTime.now().toString();
    }

    public String toJson(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error("error serializing event " + this.toString() + " to json", e);
        }

        return null;
    }

    public static String generateAvroSchema(Class clazz){
        ObjectMapper mapper = new ObjectMapper(new AvroFactory());
        mapper.findAndRegisterModules();
        AvroSchemaGenerator gen = new AvroSchemaGenerator();
        try {
            mapper.acceptJsonFormatVisitor(clazz, gen);
        } catch (JsonMappingException e) {
            log.error("Error generating Avro Schema for "+ clazz.getName(), e);
        }
        AvroSchema schemaWrapper = gen.getGeneratedSchema();

        org.apache.avro.Schema avroSchema = schemaWrapper.getAvroSchema();
        return avroSchema.toString(true);
    }
}
