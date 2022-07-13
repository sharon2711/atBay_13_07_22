package com.atBay.assignment;


import com.atBay.assignment.model.ScanStatus;
import com.atBay.assignment.service.CacheService;
import com.atBay.assignment.service.StatusService;
import com.atBay.assignment.service.impl.StatusServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ScanServiceTests {
    @Mock
    CacheService cacheService;
    @InjectMocks
    StatusServiceImpl statusServiceImpl;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getStatusByScanIdWhenTheScanIdExist(){
        String scanId = "12345";
        Mockito.when(cacheService.getStatus(any())).thenReturn(ScanStatus.COMPLETE);
        ScanStatus result = statusServiceImpl.getStatusByScanId(scanId);
        Assertions.assertEquals(result, ScanStatus.COMPLETE);
    }

    @Test
    void getStatusByScanIdWhenTheScanIdDoesntExist(){
        String scanId = "12345";
        Mockito.when(cacheService.getStatus(any())).thenReturn(null);
        ScanStatus result = statusServiceImpl.getStatusByScanId(scanId);
        Assertions.assertEquals(result, ScanStatus.NOT_FOUND);
    }
}
