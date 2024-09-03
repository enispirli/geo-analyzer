package com.interview.geoanalyzer.controller;

import com.interview.geoanalyzer.dto.input.UserInfoInputDTO;
import com.interview.geoanalyzer.dto.output.UserInfoOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
public interface UserController {

    @PostMapping
    ResponseEntity<UserInfoOutputDTO> createUser(@RequestBody UserInfoInputDTO inputDTO);
}
