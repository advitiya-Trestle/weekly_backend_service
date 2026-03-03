package com.trestleiq.weeklybackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CloudWatchMetric {
    @JsonProperty("apiName")
    private String apiName;
    
    @JsonProperty("totalCount")
    private long totalCount;
    
    @JsonProperty("avgLatency")
    private double avgLatency;
    
    @JsonProperty("p95Latency")
    private double p95Latency;
    
    @JsonProperty("p99Latency")
    private double p99Latency;
    
    @JsonProperty("responses2xx")
    private long responses2xx;
    
    @JsonProperty("errors4xx")
    private long errors4xx;
    
    @JsonProperty("errors5xx")
    private long errors5xx;
}