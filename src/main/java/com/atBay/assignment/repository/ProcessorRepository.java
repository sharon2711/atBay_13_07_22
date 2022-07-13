package com.atBay.assignment.repository;

import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanStatus;

public interface ProcessorRepository {
  void create(Scan scan);
  Scan getScanById(String scanId);
  void updateScanStatus(String scanId, ScanStatus scanStatus);
}
