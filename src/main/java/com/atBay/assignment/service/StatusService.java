package com.atBay.assignment.service;

import com.atBay.assignment.model.ScanStatus;
public interface StatusService {
    ScanStatus getStatusByScanId(String scanId);
}
