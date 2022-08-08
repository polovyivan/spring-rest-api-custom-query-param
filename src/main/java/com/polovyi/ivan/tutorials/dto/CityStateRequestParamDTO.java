package com.polovyi.ivan.tutorials.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityStateRequestParamDTO {

    private String city;

    private String state;

}
