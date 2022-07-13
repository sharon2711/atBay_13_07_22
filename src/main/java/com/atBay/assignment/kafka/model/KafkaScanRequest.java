package com.atBay.assignment.kafka.model;


import com.atBay.assignment.model.Scan;

public class KafkaScanRequest {
    private String messageId;
    private Scan data;

    public KafkaScanRequest(){}
    public KafkaScanRequest(String messageId, Scan data) {
        this.messageId = messageId;
        this.data = data;
    }
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public Scan getData() {
        return data;
    }

    public void setData(Scan data) {
        this.data = data;
    }
}
