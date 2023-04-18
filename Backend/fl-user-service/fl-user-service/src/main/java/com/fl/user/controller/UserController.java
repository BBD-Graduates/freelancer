package com.fl.user.controller;

import com.fl.user.model.FlResponse;
import com.fl.user.model.request.UserRequest;
import com.fl.user.service.serviceInterface.UserService;
import com.fl.user.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static com.fl.user.config.Constant.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<String>> addUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK,userService.insertUser(userRequest), REGISTERED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null,  REGISTRATION_FAILED );
        }
    }


}
