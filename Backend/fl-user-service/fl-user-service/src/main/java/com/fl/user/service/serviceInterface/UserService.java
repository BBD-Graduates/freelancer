package com.fl.user.service.serviceInterface;

import com.fl.user.model.request.UserLanguageRequest;
import com.fl.user.model.request.UserRequest;
import com.fl.user.model.response.UserResponse;

import java.util.List;

public interface UserService {

    Integer insertUser(UserRequest userRequest);

    String updateUser(Integer userId, UserRequest userRequest);

    List<UserResponse> getUsers(List<Integer> languageIds, Integer userId,  List<Integer> skillIds, Integer countryId,String email,Integer categoryId);

    String insertUserLanguages(List<UserLanguageRequest> userLanguageRequestList);


}
