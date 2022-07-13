package com.atBay.assignment.service;

import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanRequest;
import com.atBay.assignment.model.ScanStatus;

public interface ProcessorService {
     String create(ScanRequest scanRequest) throws Exception;
     void updateScanStatus(String scanId, ScanStatus scanStatus);
     void processScan(Scan scan);
     Scan getScanById(String scanId);
}
