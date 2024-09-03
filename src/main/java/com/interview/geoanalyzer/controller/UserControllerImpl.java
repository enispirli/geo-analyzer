package com.interview.geoanalyzer.controller;

import com.interview.geoanalyzer.dto.input.UserInfoInputDTO;
import com.interview.geoanalyzer.dto.output.UserInfoOutputDTO;
import com.interview.geoanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserInfoOutputDTO> createUser(UserInfoInputDTO inputDTO) {
        UserInfoOutputDTO outputDTO = userService.createUser(inputDTO);
        return ResponseEntity.ok(outputDTO);
    }
}
