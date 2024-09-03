package com.interview.geoanalyzer.service;

import com.interview.geoanalyzer.dto.input.UserInfoInputDTO;
import com.interview.geoanalyzer.dto.output.UserInfoOutputDTO;
import com.interview.geoanalyzer.model.Device;
import com.interview.geoanalyzer.model.User;
import com.interview.geoanalyzer.repository.DeviceRepository;
import com.interview.geoanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final DeviceRepository deviceRepository;

    @Transactional
    @Override
    public UserInfoOutputDTO createUser(UserInfoInputDTO inputDTO) {
        User user = getUser(inputDTO.getUsername());
        Device device = getDevice(inputDTO, user);

        UserInfoOutputDTO outputDTO= new UserInfoOutputDTO();
        outputDTO.setUserId(user.getId());
        outputDTO.setDeviceId(device.getId());
        return outputDTO;
    }

    private User getUser(String username) {
        Optional<User> userWrapper = userRepository.findByUsername(username);

        if (userWrapper.isPresent()) {
            return userWrapper.get();
        }

        User newUser = new User();
        newUser.setUsername(username);
        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    private Device getDevice(UserInfoInputDTO inputDTO, User user) {
        Optional<Device> deviceWrapper = deviceRepository.findByDeviceImeiAndUserId(inputDTO.getDeviceImei(), user.getId());

        if (deviceWrapper.isPresent()) {
            return deviceWrapper.get();
        }

        Device device = new Device();
        device.setUser(user);
        device.setDeviceImei(inputDTO.getDeviceImei());
        device.setDeviceName(inputDTO.getDeviceName());
        Device savedDevice = deviceRepository.save(device);
        return savedDevice;
    }
}
