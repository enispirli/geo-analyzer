package com.interview.geoanalyzer.service;

import com.interview.geoanalyzer.dto.input.DeviceLocationInputDTO;
import com.interview.geoanalyzer.dto.output.DeviceLocationOutputDTO;

public interface LocalService {

    DeviceLocationOutputDTO sendLocation(DeviceLocationInputDTO inputDTO);
}
