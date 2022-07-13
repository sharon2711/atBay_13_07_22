package com.atBay.assignment.repository;

import com.atBay.assignment.model.ScanStatus;

public interface CacheRepository {
    void setKeyAndExpireTime(String scanId, ScanStatus scanStatus);
    void updateStatus(String scanId, ScanStatus scanStatus);
    ScanStatus get(String scanId);
    Boolean isKeyExist(String scanId);

}
