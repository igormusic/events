CREATE TABLE AuditLog (
    id VARCHAR(50) PRIMARY KEY,
    source VARCHAR(150),
    type VARCHAR(150),
    resourceId VARCHAR(200) NULL,
    time DATE,
    userId VARCHAR(100)  NULL,
    correlationId VARCHAR(50) NULL,
    eventData CLOB
);