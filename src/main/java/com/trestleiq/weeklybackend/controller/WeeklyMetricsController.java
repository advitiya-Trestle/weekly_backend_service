package com.trestleiq.weeklybackend.controller;

import com.trestleiq.weeklybackend.model.WeeklyMetricsResponse;
import com.trestleiq.weeklybackend.service.WeeklyMetricsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class WeeklyMetricsController {
    
    private final WeeklyMetricsService weeklyMetricsService;
    
    public WeeklyMetricsController() {
        this.weeklyMetricsService = WeeklyMetricsService.getInstance();
    }
    
    @GetMapping("/weekly-metrics/{weekStartDate}")
    public ResponseEntity<WeeklyMetricsResponse> getWeeklyMetrics(
            @PathVariable String weekStartDate) {
        
        log.info("Received request for weekly metrics: {}", weekStartDate);
        
        try {
            WeeklyMetricsResponse response = weeklyMetricsService.getWeeklyMetrics(weekStartDate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error retrieving weekly metrics for date: {}", weekStartDate, e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Weekly Backend Service is running");
    }
}