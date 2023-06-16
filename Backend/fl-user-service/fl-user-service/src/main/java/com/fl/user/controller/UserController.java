package com.fl.user.controller;

import com.fl.user.model.FlResponse;
import com.fl.user.model.request.UserLanguageRequest;
import com.fl.user.model.request.UserRequest;
import com.fl.user.model.response.UserResponse;
import com.fl.user.service.serviceInterface.UserService;
import com.fl.user.util.FlResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fl.user.config.Constant.*;

@RestController
@RequestMapping("/flu/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final FlResponseUtil flResponseUtil;

    @PostMapping
    public ResponseEntity<FlResponse<Integer>> addUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userService.insertUser(userRequest), REGISTERED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, REGISTRATION_FAILED);
        }
    }

    @PostMapping("/languages")
    public ResponseEntity<FlResponse<String>> addUserLanguages(@Valid @RequestBody List<UserLanguageRequest> userLanguageRequestList) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userService.insertUserLanguages(userLanguageRequestList), UPDATED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, UPDATION_FAILED);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<FlResponse<String>> updateUser(@PathVariable("userId") Integer userId, @Valid @RequestBody UserRequest userRequest) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userService.updateUser(userId, userRequest), UPDATED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, UPDATION_FAILED);
        }
    }

    @GetMapping
    public ResponseEntity<FlResponse<List<UserResponse>>> getUsers(@RequestParam(name = "languageId", required = false) List<Integer> languageIds,
                                                                   @RequestParam(name = "userId", required = false) Integer userId,
                                                                   @RequestParam(name = "skillIds", required = false) List<Integer> skillIds,
                                                                   @RequestParam(name = "categoryId", required = false) Integer categoryId,
                                                                   @RequestParam(name = "countryId", required = false) Integer countryId,
                                                                   @RequestParam(name="email",required = false)String email) {
        try {
            return flResponseUtil.getResponseEntity(HttpStatus.OK, userService.getUsers(languageIds, userId, skillIds, countryId,email,categoryId), FETCHED_SUCCESSFULLY);
        } catch (Exception e) {
            return flResponseUtil.getResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, NO_RECORD_FOUND);
        }
    }


}
