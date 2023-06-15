package com.fl.user.service;

import com.fl.user.config.Constant;
import com.fl.user.model.request.UserLocationRequest;
import com.fl.user.model.response.UserLocationResponse;
import com.fl.user.repository.DbQueries;
import com.fl.user.service.serviceInterface.UserLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fl.user.config.Constant.*;

@Service
@RequiredArgsConstructor
public class UserLocationServiceImpl implements UserLocationService {
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    @Override
    public String addUserLocation(UserLocationRequest userLocationRequest) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddUserLocation(),userLocationRequest.getUserId(),userLocationRequest.getCityId(),
                    userLocationRequest.getPincode(),userLocationRequest.getAddress1(),userLocationRequest.getAddress2());
            if (insertStatus > 0) {
                return USER_LOCATION+ INSERTION_SUCCESSFUL;
            } else {
                return USER_LOCATION+ INSERTION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateUserLocation(Integer userId, UserLocationRequest userLocationRequest) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateUserLocation(),userLocationRequest.getCityId(),
                    userLocationRequest.getPincode(),userLocationRequest.getAddress1(),userLocationRequest.getAddress2(),userId);
            if (updateStatus > 0) {
                return USER_LOCATION+ UPDATED_SUCCESSFULLY;
            } else {
                return USER_LOCATION+ UPDATION_FAILED;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserLocationResponse> getUserLocation(Integer userId) {
        try {
            return jdbcTemplate.query(dbQueries.getUserLocation(), BeanPropertyRowMapper.newInstance(UserLocationResponse.class),userId);

        }catch (Exception e){
            throw e;
        }
    }
}
