package com.interview.geoanalyzer.repository;

import com.interview.geoanalyzer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCountryAndCityAndDistrict(String country, String city, String district);
}
