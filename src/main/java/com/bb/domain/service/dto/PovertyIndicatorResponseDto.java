package com.bb.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PovertyIndicatorResponseDto {
    private Integer id;
    private String year;
    private Double index;
    private String country;
}
