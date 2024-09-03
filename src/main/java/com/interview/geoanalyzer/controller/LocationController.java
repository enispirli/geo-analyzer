package com.interview.geoanalyzer.controller;

import com.interview.geoanalyzer.dto.input.DeviceLocationInputDTO;
import com.interview.geoanalyzer.dto.output.DeviceLocationOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/location")
public interface LocationController {

    @PostMapping
    ResponseEntity<DeviceLocationOutputDTO> sendLocation(@RequestBody DeviceLocationInputDTO inputDTO);
}
