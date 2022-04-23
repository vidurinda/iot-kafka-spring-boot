package com.ices.iotvisualizer.service;

import com.ices.iotvisualizer.dto.UserDto;

import com.ices.iotvisualizer.model.AppUser;

public interface UserService {

    public AppUser save(UserDto user);
}
