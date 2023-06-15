package com.fl.user.service.serviceInterface;

import com.fl.user.model.request.UserLocationRequest;
import com.fl.user.model.response.UserLocationResponse;
import com.fl.user.model.response.UserResponse;
import java.util.List;

public interface UserLocationService {

    String addUserLocation(UserLocationRequest userLocationRequest);
    String updateUserLocation(Integer userId, UserLocationRequest userLocationRequest);
    List<UserLocationResponse> getUserLocation(Integer userId);



}
