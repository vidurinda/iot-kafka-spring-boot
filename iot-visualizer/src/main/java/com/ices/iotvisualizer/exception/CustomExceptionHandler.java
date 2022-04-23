package com.ices.iotvisualizer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundExceptions(NotFoundException exception, WebRequest request){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Requested resource not found", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Object> handleUnAuthorizedExceptions(UnauthorizedAccessException exception, WebRequest request){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Permission Denied", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> handleUnAuthorizedExceptions(InvalidParameterException exception, WebRequest request){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Invalid request parameters", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUnAuthorizedExceptions(RuntimeException exception, WebRequest request){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Invalid request parameters", errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
