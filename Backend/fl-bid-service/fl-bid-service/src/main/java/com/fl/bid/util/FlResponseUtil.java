package com.fl.bid.util;

import com.fl.bid.model.FlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlResponseUtil {
    public <T> ResponseEntity<FlResponse<T>> getResponseEntity(HttpStatus status, T response, String message){
        return ResponseEntity.status(status).body(new FlResponse<T> (response,message));
    }
}
