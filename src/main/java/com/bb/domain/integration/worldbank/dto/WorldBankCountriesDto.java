package com.bb.domain.integration.worldbank.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import com.bb.domain.service.dto.CountriesResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(namespace = "http://www.worldbank.org", name = "countries")
@XmlAccessorType(XmlAccessType.NONE)
public class WorldBankCountriesDto {
    @XmlElement(name="country", namespace = "http://www.worldbank.org")
    List<Country> country;

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Getter
    public static class Country {
        @XmlElement(name="name", namespace = "http://www.worldbank.org")
        private String countryName;
        @XmlID
        @XmlAttribute(name="id")
        private String countryCode;
    }

    public List<CountriesResponseDto> parseToCountriesResponseDto() {
        return this.country.stream().map(
            c -> CountriesResponseDto.builder()
                .code(c.getCountryCode())
                .country(c.getCountryName())
                .build()
        ).toList();
    }
}
