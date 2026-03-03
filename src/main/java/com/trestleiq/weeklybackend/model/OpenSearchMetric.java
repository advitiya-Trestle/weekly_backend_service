package com.trestleiq.weeklybackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenSearchMetric {
    @JsonProperty("apiName")
    private String apiName;
    
    @JsonProperty("requestCount")
    private long requestCount;
}