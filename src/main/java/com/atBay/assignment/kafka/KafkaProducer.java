package com.atBay.assignment.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
@Component
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    public void sendKafkaMessage(String topic, String key, String message) {
        logger.info(String.format("### -> Producer send with key -> %s", key));
        kafkaTemplate.send(topic, key, message);
    }
}
