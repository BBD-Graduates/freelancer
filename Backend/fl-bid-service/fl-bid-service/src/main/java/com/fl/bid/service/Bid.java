package com.fl.bid.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bid")
public class Bid {
    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity<String>("bid microservices", HttpStatus.OK);
    }
}
