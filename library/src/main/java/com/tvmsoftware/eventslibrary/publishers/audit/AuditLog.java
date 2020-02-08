package com.tvmsoftware.eventslibrary.publishers.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditLog {

    @Id
    String id;
    String source;
    String type;
    String resourceId;
    String time;
    String userId;
    String correlationId;

    @Lob
    String eventData;
}
