package com.project.unitech.exceptionhandler;

import com.project.unitech.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Pin is already registered!")
    @ExceptionHandler(PinNotUniqueException.class)
    public void handleException(PinNotUniqueException e) {
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Invalid pin occurred!")
    @ExceptionHandler(InvalidPinException.class)
    public void handleException(InvalidPinException e) {
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "User not found!")
    @ExceptionHandler(UserNotFoundException.class)
    public void handleException(UserNotFoundException e) {
    }

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Invalid credentials! Check your data and try again!")
    @ExceptionHandler(InvalidCredentialsException.class)
    public void handleException(InvalidCredentialsException e) {
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Insufficient balance! Check your balance and try again!")
    @ExceptionHandler(InsufficientBalanceException.class)
    public void handleException(InsufficientBalanceException e) {
    }

    @ResponseStatus(
            value = HttpStatus.BAD_REQUEST,
            reason = "Unable to make transfer!")
    @ExceptionHandler(InvalidTransferException.class)
    public void handleException(InvalidTransferException e) {
    }

}


