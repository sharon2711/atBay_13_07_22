DROP TABLE IF EXISTS scan;

CREATE TABLE scan (
    id bigint auto_increment,
    scan_id VARCHAR(50) not null,
    name VARCHAR(50) not null,
    status VARCHAR(20) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY key(id)
);

CREATE INDEX scan_id_index
ON scan (scan_id);