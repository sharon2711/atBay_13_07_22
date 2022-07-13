package com.atBay.assignment.controller;

import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/status/scan")
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping(path="/{scanId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ScanStatus getStatusByScanId(@PathVariable String scanId) {
       return statusService.getStatusByScanId(scanId);
    }
}
