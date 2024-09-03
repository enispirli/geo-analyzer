package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
