package com.parcial.reservation_service.dto;

import lombok.Data;

@Data
public class FeatureDTO {
    private Integer id;
    private Boolean hasSwimmingPool;
    private Boolean hasBuffet;
    private Boolean hasWifi;
    private Boolean hasFridge;
}
