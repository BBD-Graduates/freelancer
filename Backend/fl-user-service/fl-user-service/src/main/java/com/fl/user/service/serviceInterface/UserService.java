package com.fl.user.service.serviceInterface;

import com.fl.user.model.request.UserLanguageRequest;
import com.fl.user.model.request.UserRequest;
import com.fl.user.model.response.UserResponse;

import java.util.List;

public interface UserService {

    String insertUser(UserRequest userRequest);
    String updateUser( Integer userId,UserRequest userRequest);

    List<UserResponse> getUsers(Integer languageId,Integer userId,Integer skillId);

    String insertUserLanguages(List<UserLanguageRequest> userLanguageRequestList);


}
