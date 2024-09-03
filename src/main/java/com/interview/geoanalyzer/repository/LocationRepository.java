package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
