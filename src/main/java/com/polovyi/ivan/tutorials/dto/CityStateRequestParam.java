package com.polovyi.ivan.tutorials.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class CityStateRequestParam {

    private Set<@NotNull(message = "The query param cities is invalid") CityStateRequestParamDTO> citiesWithStates;

    public CityStateRequestParam(String holidayCitiesWithStatesString) {
        this.citiesWithStates = Stream.of(StringUtils.split(holidayCitiesWithStatesString, ","))
                .map(s -> {
                    String city = StringUtils.substringBeforeLast(s, "-");
                    String state = StringUtils.strip(StringUtils.substringAfterLast(s, "-"), StringUtils.SPACE);
                    return StringUtils.isAnyBlank(city, state) ? null : new CityStateRequestParamDTO(city, state);
                }).collect(Collectors.toSet());
    }
}
