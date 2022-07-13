package com.atBay.assignment.model;

import com.atBay.assignment.utils.Utils;

public class ScanRequest {
    private String name;
    public ScanRequest() {}

    public ScanRequest(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Scan toScan(){
        return new Scan(
                0L,
                Utils.getUUIDString(),
                this.name,
                ScanStatus.ACCEPTED
        );
    }
}
