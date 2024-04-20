package com.parcial.reservation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PhoneNumber {
    private Integer id;
    private String type;
    private String value;
}
