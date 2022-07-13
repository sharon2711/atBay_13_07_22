package com.atBay.assignment.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);
    static ObjectMapper objectMapper = new ObjectMapper();
    public static String getUUIDString() {
        return UUID.randomUUID().toString();
    }

    public static String mapperObjectToString(Object obj) throws Exception  {
        try {
            return objectMapper.writer().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.info("Could not deserialize kafka message with error: " + e.getMessage());
            throw e;
        }
    }
}
