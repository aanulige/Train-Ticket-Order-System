package com.example.train_ticket_system.enumeration;

import org.springframework.http.HttpStatus;

public enum Status {
    OK(1000, "Successful", HttpStatus.OK),
    PASSWORDS_NOT_MATCHED(2000, "Passwords are not matched", HttpStatus.BAD_REQUEST),
    USER_DOES_NOT_EXIST(2001, "User does not exist", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS(2002, "Username already exists", HttpStatus.CONFLICT),
    UNKNOWN_ERROR(9999, "Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);


    final int code;
    final String message;
    final HttpStatus httpStatus;
    Status(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
