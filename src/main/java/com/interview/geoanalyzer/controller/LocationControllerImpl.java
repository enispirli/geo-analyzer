package com.interview.geoanalyzer.controller;

import com.interview.geoanalyzer.dto.input.DeviceLocationInputDTO;
import com.interview.geoanalyzer.dto.output.DeviceLocationOutputDTO;
import com.interview.geoanalyzer.service.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationControllerImpl implements LocationController {

    private final LocalService localService;

    @Override
    public ResponseEntity<DeviceLocationOutputDTO> sendLocation(DeviceLocationInputDTO inputDTO) {
        DeviceLocationOutputDTO deviceLocationOutputDTO = localService.sendLocation(inputDTO);
        return ResponseEntity.ok(deviceLocationOutputDTO);
    }
}
