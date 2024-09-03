package com.interview.geoanalyzer.dto.input;

import lombok.Data;

@Data
public class DeviceLocationInputDTO {

    private Long deviceId;

    private Double lat;

    private Double lon;
}
