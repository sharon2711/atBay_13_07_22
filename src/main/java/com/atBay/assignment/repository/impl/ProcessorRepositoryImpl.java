package com.atBay.assignment.repository.impl;

import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.repository.ProcessorRepository;
import com.atBay.assignment.repository.mapper.ScanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProcessorRepositoryImpl implements ProcessorRepository{

    private static final String SCAN_TABLE_NAME = "scan";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProcessorRepositoryImpl.class);

    @Override
    public void create(Scan scan) {
        String sql = "INSERT INTO " + SCAN_TABLE_NAME + "(name, scan_id, status) values (?, ?, ?)";
        jdbcTemplate.update(
             sql,
             scan.getName(),
             scan.getScanId(),
             scan.getStatus().name()
        );
    }
    @Override
    public Scan getScanById(String scanId) {
        String sql = "SELECT * FROM " + SCAN_TABLE_NAME + " WHERE scan_id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new ScanMapper(), scanId);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Warning: EmptyResultDataAccessException");
            return null;
        }
    }
    @Override
    public void updateScanStatus(String scanId, ScanStatus scanStatus) {
        String sql = "UPDATE " + SCAN_TABLE_NAME + " SET status = ? WHERE scan_id = ?";
         jdbcTemplate.update(sql, scanStatus.name(), scanId);
    }
}
