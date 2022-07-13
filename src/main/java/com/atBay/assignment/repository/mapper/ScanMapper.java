package com.atBay.assignment.repository.mapper;

import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanStatus;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ScanMapper implements RowMapper<Scan> {
    @Override
    public Scan mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Scan(
                rs.getLong("id"),
                rs.getString("scan_id"),
                rs.getString("name"),
                ScanStatus.valueOf(rs.getString("status"))
        );
    }
}
