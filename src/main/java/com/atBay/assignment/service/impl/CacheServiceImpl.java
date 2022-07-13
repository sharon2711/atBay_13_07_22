package com.atBay.assignment.service.impl;

import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.repository.CacheRepository;
import com.atBay.assignment.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    CacheRepository cacheRepository;
    @Override
    public void setStatusKeyAndExpireTime(String scanId, ScanStatus scanStatus) {
        cacheRepository.setKeyAndExpireTime(scanId, scanStatus);
    }
    @Override
    public void updateStatus(String scanId, ScanStatus scanStatus) {
        cacheRepository.updateStatus(scanId, scanStatus);
    }
    @Override
    public ScanStatus getStatus(String scanId) {
        if (isKeyExist(scanId)) {
             return cacheRepository.get(scanId);
        }
        return null;
    }
    private Boolean isKeyExist(String scanId) {
        return cacheRepository.isKeyExist(scanId);
    }
}
