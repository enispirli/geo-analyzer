package com.interview.geoanalyzer.service;

import com.interview.geoanalyzer.dto.input.UserInfoInputDTO;
import com.interview.geoanalyzer.dto.output.UserInfoOutputDTO;

public interface UserService {

    UserInfoOutputDTO createUser(UserInfoInputDTO inputDTO);
}
