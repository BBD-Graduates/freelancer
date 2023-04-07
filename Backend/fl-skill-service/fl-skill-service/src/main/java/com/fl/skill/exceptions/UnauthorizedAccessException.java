package com.fl.skill.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends Exception {
 HttpStatus httpStatus;
    public UnauthorizedAccessException(String message){
        super(message);

    }
    
}
