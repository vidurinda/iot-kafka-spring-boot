package com.ices.iotvisualizer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorResponse {

    private String message;

    private List<String> details;

}
