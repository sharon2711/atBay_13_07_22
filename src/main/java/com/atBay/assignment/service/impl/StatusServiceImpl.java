package com.atBay.assignment.service.impl;

import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.service.CacheService;
import com.atBay.assignment.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    CacheService cacheService;

    @Override
    public ScanStatus getStatusByScanId(String scanId) {
        ScanStatus scanStatus = cacheService.getStatus(scanId);
        if (scanStatus!= null) {
            return scanStatus;
        } else {
            return ScanStatus.NOT_FOUND;
        }
    }
}
