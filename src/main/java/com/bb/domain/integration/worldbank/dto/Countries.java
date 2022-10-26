package com.bb.domain.integration.worldbank.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.bb.domain.service.dto.CountriesResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import org.jboss.resteasy.annotations.providers.jaxb.json.XmlNsMap;
import org.jboss.resteasy.annotations.providers.jaxb.json.Mapped;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(namespace = "http://www.worldbank.org")
@Mapped(namespaceMap = @XmlNsMap(jsonName = "wb", namespace = "http://www.worldbank.org"))
@XmlAccessorType(XmlAccessType.NONE)
public class Countries {
    @XmlElement(name="country",namespace = "wb")
    List<Country> country;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Country {
        private String name;
    }

    public List<CountriesResponseDto> parseToCountriesResponseDto() {
        return null;
    }
}
