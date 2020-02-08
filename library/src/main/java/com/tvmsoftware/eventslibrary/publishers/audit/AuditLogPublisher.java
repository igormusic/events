package com.tvmsoftware.eventslibrary.publishers.audit;

import com.tvmsoftware.eventslibrary.model.Event;
import com.tvmsoftware.eventslibrary.EventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class AuditLogPublisher implements EventPublisher {
    private final AuditLogRepository repository;

    public AuditLogPublisher(AuditLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends Event> boolean canPublish(T event) {
        // TODO: implement configuration list for event.type that need to be logged
        if(event.getUserId()!=null){
            return true;
        }

        return false;
    }

    @Override
    public <T extends Event> void publish(T event) {
        AuditLog auditLog =
            new AuditLog(
                    event.getId(),
                    event.getSource(),
                    event.getType(),
                    event.getResourceId(),
                    event.getTime(),
                    event.getUserId(),
                    event.getCorrelationId(),
                    event.toJson());

        repository.save(auditLog);

        log.info("stored event " + event.getId() + " in audit log");
    }


}
