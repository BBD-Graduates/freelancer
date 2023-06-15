package com.fl.user.controller;

import com.fl.user.model.FlResponse;
import com.fl.user.model.request.UserLocationRequest;
import com.fl.user.model.response.UserLocationResponse;
import com.fl.user.service.serviceInterface.UserLocationService;
import com.fl.user.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.user.config.Constant.*;

@RestController
@RequestMapping("/userLocation")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserLocationController {

    private final UserLocationService userLocationService;
    private final FlResponseUtil flResponseUtil;


    @PostMapping
    public ResponseEntity<FlResponse<String>> addUserLocation(@Valid @RequestBody UserLocationRequest userLocationRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userLocationService.addUserLocation(userLocationRequest),USER_LOCATION+INSERTION_SUCCESSFUL);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, USER_LOCATION+INSERTION_FAILED);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<FlResponse<List<UserLocationResponse>>> getUserLocation(@PathVariable("userId") Integer userId) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userLocationService.getUserLocation(userId),USER_LOCATION+FETCHED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, CANT_PROCESS_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FlResponse<String>> update(@PathVariable("userId") Integer userId, @Valid @RequestBody UserLocationRequest userLocationRequest ) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userLocationService.updateUserLocation(userId,userLocationRequest),USER_LOCATION+UPDATED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, USER_LOCATION+UPDATION_FAILED);
        }
    }

}
