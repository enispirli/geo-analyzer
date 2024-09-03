package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
