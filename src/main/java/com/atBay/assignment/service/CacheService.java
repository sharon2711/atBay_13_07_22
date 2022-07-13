package com.atBay.assignment.service;

import com.atBay.assignment.model.ScanStatus;

public interface CacheService {
    void setStatusKeyAndExpireTime(String scanId, ScanStatus scanStatus);
    void updateStatus(String scanId, ScanStatus scanStatus);
    ScanStatus getStatus(String key);
}
