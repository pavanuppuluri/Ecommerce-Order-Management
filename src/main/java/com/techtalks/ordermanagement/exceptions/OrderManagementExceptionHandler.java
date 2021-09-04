package com.techtalks.ordermanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderManagementExceptionHandler {

@ExceptionHandler(OrderNotFoundException.class)
public final ResponseEntity<Object> orderNotFound(OrderNotFoundException exception)
{
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

}
}
