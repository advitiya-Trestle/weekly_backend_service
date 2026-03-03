package com.trestleiq.weeklybackend.service;

import com.trestleiq.weeklybackend.model.WeeklyMetricsResponse;
import com.trestleiq.weeklybackend.model.CloudWatchMetric;
import com.trestleiq.weeklybackend.model.OpenSearchMetric;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class WeeklyMetricsService {
    
    private static WeeklyMetricsService instance;
    private final Map<String, WeeklyMetricsResponse> metricsCache;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    private WeeklyMetricsService() {
        this.metricsCache = new HashMap<>();
    }
    
    public static synchronized WeeklyMetricsService getInstance() {
        if (instance == null) {
            instance = new WeeklyMetricsService();
        }
        return instance;
    }
    
    public WeeklyMetricsResponse getWeeklyMetrics(String weekStartDate) {
        log.info("Fetching weekly metrics for week starting: {}", weekStartDate);
        
        // Check if data exists in cache
        if (metricsCache.containsKey(weekStartDate)) {
            log.info("Returning cached metrics for week: {}", weekStartDate);
            return metricsCache.get(weekStartDate);
        }
        
        // Fetch new data
        log.info("Fetching new metrics data for week: {}", weekStartDate);
        WeeklyMetricsResponse metrics = fetchMetricsForWeek(weekStartDate);
        
        // Store in cache
        metricsCache.put(weekStartDate, metrics);
        
        return metrics;
    }
    
    private WeeklyMetricsResponse fetchMetricsForWeek(String weekStartDate) {
        try {
            LocalDate startDate = LocalDate.parse(weekStartDate, formatter);
            LocalDate endDate = startDate.plusDays(6); // Sunday
            
            String weekEndDate = endDate.format(formatter);
            
            WeeklyMetricsResponse response = new WeeklyMetricsResponse();
            response.setWeekStart(weekStartDate);
            response.setWeekEnd(weekEndDate);
            
            // Fetch CloudWatch metrics
            List<CloudWatchMetric> cloudwatchMetrics = fetchCloudWatchMetrics(startDate, endDate);
            response.setCloudwatchMetrics(cloudwatchMetrics);
            
            // Fetch OpenSearch metrics  
            List<OpenSearchMetric> opensearchMetrics = fetchOpenSearchMetrics(startDate, endDate);
            response.setOpensearchMetrics(opensearchMetrics);
            
            return response;
            
        } catch (Exception e) {
            log.error("Error fetching metrics for week: {}", weekStartDate, e);
            throw new RuntimeException("Failed to fetch metrics", e);
        }
    }
    
    private List<CloudWatchMetric> fetchCloudWatchMetrics(LocalDate startDate, LocalDate endDate) {
        // TODO: Implement actual CloudWatch API integration
        // For now, return sample data similar to the provided format
        List<CloudWatchMetric> metrics = new ArrayList<>();
        
        CloudWatchMetric metric1 = new CloudWatchMetric();
        metric1.setApiName("1.0 phone_data");
        metric1.setTotalCount(0);
        metric1.setAvgLatency(0);
        metric1.setP95Latency(0);
        metric1.setP99Latency(0);
        metric1.setResponses2xx(0);
        metric1.setErrors4xx(0);
        metric1.setErrors5xx(0);
        metrics.add(metric1);
        
        CloudWatchMetric metric2 = new CloudWatchMetric();
        metric2.setApiName("portal-prod-3.0 phone");
        metric2.setTotalCount(6467448);
        metric2.setAvgLatency(49);
        metric2.setP95Latency(123);
        metric2.setP99Latency(228);
        metric2.setResponses2xx(6467415);
        metric2.setErrors4xx(30);
        metric2.setErrors5xx(3);
        metrics.add(metric2);
        
        CloudWatchMetric metric3 = new CloudWatchMetric();
        metric3.setApiName("portal-prod-3.1 cnam");
        metric3.setTotalCount(513031);
        metric3.setAvgLatency(108);
        metric3.setP95Latency(247);
        metric3.setP99Latency(341);
        metric3.setResponses2xx(509414);
        metric3.setErrors4xx(3617);
        metric3.setErrors5xx(0);
        metrics.add(metric3);
        
        return metrics;
    }
    
    private List<OpenSearchMetric> fetchOpenSearchMetrics(LocalDate startDate, LocalDate endDate) {
        // TODO: Implement actual OpenSearch API integration
        // For now, return sample data similar to the provided format
        List<OpenSearchMetric> metrics = new ArrayList<>();
        
        OpenSearchMetric metric1 = new OpenSearchMetric();
        metric1.setApiName("neustar");
        metric1.setRequestCount(4108968);
        metrics.add(metric1);
        
        OpenSearchMetric metric2 = new OpenSearchMetric();
        metric2.setApiName("netnumber check");
        metric2.setRequestCount(2586913);
        metrics.add(metric2);
        
        OpenSearchMetric metric3 = new OpenSearchMetric();
        metric3.setApiName("telov21");
        metric3.setRequestCount(979896);
        metrics.add(metric3);
        
        OpenSearchMetric metric4 = new OpenSearchMetric();
        metric4.setApiName("endatov21");
        metric4.setRequestCount(344300);
        metrics.add(metric4);
        
        return metrics;
    }
}