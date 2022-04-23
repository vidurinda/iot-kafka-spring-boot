package com.ices.iotvisualizer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String username;

    private String password;
}
