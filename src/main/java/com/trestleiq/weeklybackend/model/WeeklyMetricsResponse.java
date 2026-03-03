package com.trestleiq.weeklybackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeeklyMetricsResponse {
    @JsonProperty("weekStart")
    private String weekStart;
    
    @JsonProperty("weekEnd") 
    private String weekEnd;
    
    @JsonProperty("cloudwatchMetrics")
    private List<CloudWatchMetric> cloudwatchMetrics;
    
    @JsonProperty("opensearchMetrics")
    private List<OpenSearchMetric> opensearchMetrics;
}