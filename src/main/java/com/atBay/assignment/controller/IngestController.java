package com.atBay.assignment.controller;

import com.atBay.assignment.model.ScanRequest;
import com.atBay.assignment.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/scan")
public class IngestController {
    @Autowired
    ProcessorService processorService;

    @Transactional
    @PostMapping(path = "/ingest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createScan(@RequestBody ScanRequest scanRequest) throws Exception {
        return processorService.create(scanRequest);
    }
}
