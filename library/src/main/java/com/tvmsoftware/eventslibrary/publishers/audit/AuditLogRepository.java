package com.tvmsoftware.eventslibrary.publishers.audit;

import org.springframework.data.repository.CrudRepository;

interface AuditLogRepository extends CrudRepository<AuditLog, String> {

}