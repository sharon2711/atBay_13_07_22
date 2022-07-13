package com.atBay.assignment.kafka;

import com.atBay.assignment.kafka.model.KafkaScanRequest;
import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.service.ProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    public static final String KAFKA_TOPIC_NAME = "scan-topic";
    private static final String KAFKA_GROUP_ID = "scan-topic";
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    @Autowired
    ProcessorService processorService;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = KAFKA_TOPIC_NAME, groupId = KAFKA_GROUP_ID)
    public void scanConsumer(String message) throws Exception {
        KafkaScanRequest kafkaScanRequest = mapperStringToKafkaScanRequest(message);
        logger.info(String.format("Start consuming new scan message with kafkaMessageId: %s and scanId: %s",
                kafkaScanRequest.getMessageId(), kafkaScanRequest.getData().getScanId()));
        try {
            processorService.updateScanStatus(kafkaScanRequest.getData().getScanId(), ScanStatus.RUNNING);
            processorService.processScan(kafkaScanRequest.getData());
        } catch (Exception ex) {
            logger.info(String.format("Error when tried to consume kafka scan message with error message: " + ex.getMessage()));
            processorService.updateScanStatus(kafkaScanRequest.getData().getScanId(), ScanStatus.ERROR);
        }
    }
    private KafkaScanRequest mapperStringToKafkaScanRequest(String message) throws Exception {
        try {
           return objectMapper.readValue(message, KafkaScanRequest.class);
        } catch(Exception e) {
            logger.info("Could not deserialize kafka message with error: " + e.getMessage());
            throw e;
        }
    }
}
