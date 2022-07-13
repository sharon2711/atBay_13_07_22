package com.atBay.assignment.service.impl;

import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.service.CacheService;
import com.atBay.assignment.service.ProcessorService;
import com.atBay.assignment.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    CacheService cacheService;
    @Autowired
    ProcessorService processorService;
    @Override
    public ScanStatus getStatusByScanId(String scanId) {
        ScanStatus scanStatusFromCache = cacheService.getStatus(scanId);
        if (scanStatusFromCache != null) {
            return scanStatusFromCache;
        } else {
            Scan scan = processorService.getScanById(scanId);
            if(scan != null) {
                cacheService.setStatusKeyAndExpireTime(scanId, scan.getStatus());
                return scan.getStatus();
            } else {
                return ScanStatus.NOT_FOUND;
            }
        }
    }
}
