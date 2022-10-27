package com.bb.domain.integration.worldbank.dto;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.bb.domain.service.dto.PovertyIndicatorResponseDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
public class WorldBankPovertyDto {
    List<Object> response;

    SourceInfo info;
    SourceData[] metrics;

    @JsonCreator
    @SneakyThrows
    public WorldBankPovertyDto(List<Object> data) {
        this.response = data;

        info = new ObjectMapper().readValue(new Gson().toJson(data.get(0)), SourceInfo.class);
        metrics = new ObjectMapper().readValue(new Gson().toJson(data.get(1)), SourceData[].class);
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SourceInfo {
        private Integer page;
        private Integer pages;
        @JsonProperty("per_page")
        private Integer perPage;
        private Integer total;
        private String sourceid;
        private String sourcename;
        private String lastupdated;
    }

    @Data
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SourceData {
        private Indicator indicator;
        private Country country;
        private String countryiso3code;
        private String date;
        private Double value;
        private String unit;
        @JsonProperty("obs_status")
        private String obsStatus;
        private Integer decimal;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Indicator {
        private String id;
        private String value;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Country {
        private String id;
        private String value;
    }

    public List<PovertyIndicatorResponseDto> toPovertyIndicator() {
        AtomicInteger id = new AtomicInteger();

        return Arrays.stream(this.metrics).map(metric -> 
            PovertyIndicatorResponseDto.builder()
                .country(metric.getCountry().getValue())
                .index(metric.getValue())
                .year(metric.getDate())
                .id(id.incrementAndGet())
                .build()
        ).toList();
    }
}
