package com.interview.geoanalyzer.service;

import com.google.maps.errors.ApiException;
import com.interview.geoanalyzer.dto.input.DeviceLocationInputDTO;
import com.interview.geoanalyzer.dto.output.DeviceLocationOutputDTO;
import com.interview.geoanalyzer.model.Address;
import com.interview.geoanalyzer.model.Device;
import com.interview.geoanalyzer.model.Location;
import com.interview.geoanalyzer.repository.AddressRepository;
import com.interview.geoanalyzer.repository.DeviceRepository;
import com.interview.geoanalyzer.repository.LocationRepository;
import com.interview.geoanalyzer.thirdparty.GoogleMapsService;
import com.interview.geoanalyzer.thirdparty.dto.GeoDataOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocalServiceImpl implements LocalService {

    private final GoogleMapsService googleMapsService;

    private final AddressRepository addressRepository;

    private final DeviceRepository deviceRepository;

    private final LocationRepository locationRepository;

    @Transactional
    @Override
    public DeviceLocationOutputDTO sendLocation(DeviceLocationInputDTO inputDTO) {

        try {
            GeoDataOutput output = googleMapsService.reverseGeocode(inputDTO.getLat(), inputDTO.getLon());

            Address address = getAddress(output);

            Device device = getDevice(inputDTO.getDeviceId());

            saveLocation(address, device, inputDTO);

            DeviceLocationOutputDTO deviceLocationOutputDTO = new DeviceLocationOutputDTO();
            deviceLocationOutputDTO.setCountry(output.getCountry());
            deviceLocationOutputDTO.setCity(output.getCity());
            deviceLocationOutputDTO.setDistrict(output.getDistrict());
            return deviceLocationOutputDTO;

        } catch (ApiException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void saveLocation(Address address, Device device, DeviceLocationInputDTO inputDTO) {
        Location location = new Location();
        location.setAddress(address);
        location.setDevice(device);
        location.setLat(inputDTO.getLat().toString());
        location.setLon(inputDTO.getLon().toString());
        location.setDate(LocalDateTime.now());

        locationRepository.save(location);
    }

    private Address getAddress(GeoDataOutput output) {
        Optional<Address> addressWrapper = addressRepository
                .findByCountryAndCityAndDistrict(output.getCountry(), output.getCity(), output.getDistrict());

        if (addressWrapper.isPresent()) {
            return addressWrapper.get();
        }

        Address address = new Address();
        address.setCountry(output.getCountry());
        address.setCity(output.getCity());
        address.setDistrict(output.getDistrict());
        Address savedAddress = addressRepository.save(address);
        return savedAddress;
    }

    private Device getDevice(Long deviceId) {
        Optional<Device> deviceWrapper = deviceRepository.findById(deviceId);
        if (deviceWrapper.isPresent()) {
            return deviceWrapper.get();
        }

        throw new IllegalArgumentException("Device not found");
    }
}
