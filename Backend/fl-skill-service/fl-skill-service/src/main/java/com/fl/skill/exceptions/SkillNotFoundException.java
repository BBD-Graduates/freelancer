package com.fl.skill.exceptions;

import org.springframework.http.HttpStatus;

public class SkillNotFoundException extends Exception {
 HttpStatus httpStatus;
    public SkillNotFoundException(String message){
        super(message);
        httpStatus = httpStatus.INTERNAL_SERVER_ERROR;
    }
    
}
