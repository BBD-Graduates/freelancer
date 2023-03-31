package com.fl.skill.config;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.util.FlResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class Exceptionhandler extends ResponseEntityExceptionHandler {
    private final FlResponseUtil flResponseUtil;
    @ExceptionHandler({CategoryNotFoundException.class})
    protected <T> ResponseEntity<FlResponse<T>> customException(Exception ex){
        return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR,null, ex.getMessage());
    }
    
}
