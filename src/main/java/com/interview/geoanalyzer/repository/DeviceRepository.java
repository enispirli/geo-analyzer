package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByDeviceImeiAndUserId(String deviceImei, Long userId);
}
