package com.fl.skill.config;
import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.model.FlResponse;
import com.fl.skill.util.FlResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
