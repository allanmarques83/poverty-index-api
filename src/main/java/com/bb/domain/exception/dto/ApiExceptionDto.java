package com.bb.domain.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiExceptionDto {
    private String message;
    private Integer status;
}
