package com.bb.domain.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountriesResponseDto {
    private String country;
    private String code;
}
