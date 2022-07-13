package com.atBay.assignment.service.impl;

import com.atBay.assignment.kafka.KafkaProducer;
import com.atBay.assignment.kafka.model.KafkaScanRequest;
import com.atBay.assignment.model.Scan;
import com.atBay.assignment.model.ScanRequest;
import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.repository.ProcessorRepository;
import com.atBay.assignment.service.CacheService;
import com.atBay.assignment.service.ProcessorService;
import com.atBay.assignment.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.atBay.assignment.kafka.KafkaConsumer.KAFKA_TOPIC_NAME;

@Service
public class ProcessorServiceImpl implements ProcessorService {
    private static final Logger logger = LoggerFactory.getLogger(ProcessorServiceImpl.class);

    @Autowired
    ProcessorRepository processorRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    CacheService cacheService;

    @Override
    public String create(ScanRequest scanRequest) throws Exception {
        logger.info("Start creating new scan with scan name: " + scanRequest.getName());
        Scan scan = scanRequest.toScan();
        processorRepository.create(scan);
        cacheService.setStatusKeyAndExpireTime(scan.getScanId(), scan.getStatus());
        KafkaScanRequest kafkaScanRequest = new KafkaScanRequest(Utils.getUUIDString(), scan);
        kafkaProducer.sendKafkaMessage(KAFKA_TOPIC_NAME, scan.getScanId(), Utils.mapperObjectToString(kafkaScanRequest));
        return scan.getScanId();
    }
    @Override
    public void updateScanStatus(String scanId, ScanStatus scanStatus){
      if (getScanById(scanId) != null) {
          processorRepository.updateScanStatus(scanId, scanStatus);
          cacheService.updateStatus(scanId, scanStatus);
      }
    }
    @Override
    public void processScan(Scan scan){
        logger.info("Start processing scan with scanId: " + scan.getScanId());
        updateScanStatus(scan.getScanId(), ScanStatus.COMPLETE);
        logger.info("Finish processing scan with scanId: " + scan.getScanId());
    }
    public Scan getScanById(String scanId) {
       return processorRepository.getScanById(scanId);
    }
}
