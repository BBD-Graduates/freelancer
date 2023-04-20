package com.fl.user.service;

import com.fl.user.config.Constant;
import com.fl.user.model.request.UserLanguageRequest;
import com.fl.user.model.request.UserRequest;
import com.fl.user.model.response.LanguageResponse;
import com.fl.user.model.response.UserResponse;
import com.fl.user.repository.DbQueries;
import com.fl.user.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String insertUser(UserRequest userRequest) {
        try {
            int insertStatus = jdbcTemplate.update(dbQueries.getAddUser(), userRequest.getEmail());
            if (insertStatus > 0) {
                return Constant.REGISTERED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateUser(Integer userId, UserRequest userRequest) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateUser(), userRequest.getFirstName(), userRequest.getLastName()
                    , userRequest.getCompany(), userRequest.getPhNo(), userRequest.getPhotoURL(), userId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<UserResponse> getUsers(Integer languageId) {
        try {
            List<UserResponse> userDetailsList = new ArrayList<>();
            List<UserResponse> userlanguages;
            if (!languageId.equals(0)) {
                userlanguages = jdbcTemplate.query(dbQueries.getUserDetailsByLanguageId(), BeanPropertyRowMapper.newInstance(UserResponse.class));
            } else {
                userlanguages = jdbcTemplate.query(dbQueries.getUserDetails(), BeanPropertyRowMapper.newInstance(UserResponse.class));
            }

            List<Integer> userIdList = userlanguages.stream().map(UserResponse::getUserId).distinct().collect(Collectors.toList());
            for (Integer fetchUserId : userIdList) {
                UserResponse usersDetails = new UserResponse();
                usersDetails.setUserId(fetchUserId);
                userlanguages.stream().filter(userResponse -> userResponse.getUserId() == fetchUserId).forEach(userResponse -> {
                    usersDetails.setFirstName(userResponse.getFirstName());
                    usersDetails.setLastName(userResponse.getLastName());
                    usersDetails.setEmail(userResponse.getEmail());
                    usersDetails.setCompany(userResponse.getCompany());
                    usersDetails.setPhNo(userResponse.getPhNo());
                    usersDetails.setIsVerified(userResponse.getIsVerified());
                    usersDetails.setPhotoURL(userResponse.getPhotoURL());
                    usersDetails.setCreatedDate(userResponse.getCreatedDate());
                    usersDetails.getLanguages().add(LanguageResponse.builder().languageId(userResponse.getLanguageId())
                            .languageName(userResponse.getLanguageName()).build());
                });
                userDetailsList.add(usersDetails);
            }
            return userDetailsList;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String insertUserLanguages(List<UserLanguageRequest> userLanguageRequestList) {
        try {
            int[] insertStatus = jdbcTemplate.batchUpdate(dbQueries.getAdduserLanguages(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int row) throws SQLException {
                            ps.setInt(1, userLanguageRequestList.get(row).getUserId());
                            ps.setInt(2, userLanguageRequestList.get(row).getLanguageId());
                        }

                        @Override
                        public int getBatchSize() {
                            return userLanguageRequestList.size();
                        }
                    }
            );
            if (insertStatus.length > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
