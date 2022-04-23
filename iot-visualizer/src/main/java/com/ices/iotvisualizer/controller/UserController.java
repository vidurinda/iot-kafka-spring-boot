package com.ices.iotvisualizer.controller;

import com.ices.iotvisualizer.dto.UserDto;
import com.ices.iotvisualizer.model.AppUser;
import com.ices.iotvisualizer.security.CustomeUserDetailsService;
import com.ices.iotvisualizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        return ResponseEntity.ok(userService.save(user));
    }
}
