package com.ices.iotvisualizer.service;

import com.ices.iotvisualizer.dto.UserDto;
import com.ices.iotvisualizer.exception.InvalidParameterException;
import com.ices.iotvisualizer.model.AppUser;
import com.ices.iotvisualizer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser save(UserDto user) {
        try {
            AppUser appUser = new AppUser();
            appUser.setId(UUID.randomUUID());
            appUser.setUsername(user.getUsername());
            appUser.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(appUser);
        } catch (RuntimeException e){
            log.error("Error when saving user {}",e);
            throw new InvalidParameterException("Error when saving user");
        } catch (Exception e){
            log.error("Error when saving user {}",e);
        }
        return null;
    }
}
