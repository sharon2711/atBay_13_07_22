package com.atBay.assignment.model;

public class Scan {
    private Long id;
    private String scanId;
    private String name;
    private ScanStatus status;

    public Scan(){}
    public Scan(Long id, String scanId, String name, ScanStatus status) {
        this.id = id;
        this.scanId = scanId;
        this.name = name;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScanId() {
        return scanId;
    }

    public void setScanId(String scanId) {
        this.scanId = scanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScanStatus getStatus() {
        return status;
    }

    public void setStatus(ScanStatus status) {
        this.status = status;
    }
}
