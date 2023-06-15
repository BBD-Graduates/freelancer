package com.fl.user.service;

import com.fl.user.config.Constant;
import com.fl.user.feignClient.UserProjectClient;
import com.fl.user.feignClient.UserSkillClient;
import com.fl.user.model.FlResponse;
import com.fl.user.model.request.UserLanguageRequest;
import com.fl.user.model.request.UserRequest;
import com.fl.user.model.response.LanguageResponse;
import com.fl.user.model.response.RatingResponse;
import com.fl.user.model.response.UserResponse;
import com.fl.user.model.response.UserSkillsResponse;
import com.fl.user.repository.DbQueries;
import com.fl.user.service.serviceInterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final DbQueries dbQueries;
    private final UserSkillClient userSkillClient;
    private final UserProjectClient userProjectClient;


    @Override
    public Integer insertUser(UserRequest userRequest) {
        try {
            KeyHolder identityValue = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(dbQueries.getAddUser(), Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, userRequest.getFirstName());
                ps.setString(2, userRequest.getLastName());
                ps.setString(3, userRequest.getEmail());
                ps.setString(4,userRequest.getPhotoUrl());
                return ps;
            }, identityValue);

            Number userId = identityValue.getKey().intValue();

            if (userId.intValue() > 0) {
                return userId.intValue();
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateUser(Integer userId, UserRequest userRequest) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateUser(), userRequest.getFirstName(), userRequest.getLastName()
                    , userRequest.getHeadLine(), userRequest.getSummary(), userRequest.getCompany(), userRequest.getPhNo(), userId);
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
    public List<UserResponse> getUsers(List<Integer> languageIds, Integer userId,  List<Integer> skillIds, Integer countryId, String email, Integer categoryId) {
        try {
            FlResponse<List<UserSkillsResponse>> userList;
            List<UserResponse> userDetails;
            if (skillIds != null) {
                userList = userSkillClient.getUserSkillBySkillId(skillIds);
                List<Integer> userIdList = userList.getResponse().stream().map(UserSkillsResponse::getUserId).distinct().toList();
                SqlParameterSource parameters = new MapSqlParameterSource("userId", userIdList);
                userDetails = namedParameterJdbcTemplate.query(dbQueries.getUserDetailsInUserIds(), parameters, BeanPropertyRowMapper.newInstance(UserResponse.class));
                for (UserResponse userDetail : userDetails) {
                    userDetail.setSkills(userList.getResponse().stream().filter(user -> user.getUserId() == userDetail.getUserId()).findFirst().get().getSkills());
                    List<LanguageResponse> languageList = getUserLanguage(userDetail.getUserId());
                    userDetail.setLanguages(languageList);
                }
                return userDetails;
            } else if (categoryId != null) {
                userList = userSkillClient.getUserSkillByCategoryId(categoryId);
                List<Integer> userIdList = userList.getResponse().stream().map(UserSkillsResponse::getUserId).distinct().toList();
                SqlParameterSource parameters = new MapSqlParameterSource("userId", userIdList);
                userDetails = namedParameterJdbcTemplate.query(dbQueries.getUserDetailsInUserIds(), parameters, BeanPropertyRowMapper.newInstance(UserResponse.class));
                for (UserResponse userDetail : userDetails) {
                    userDetail.setSkills(userList.getResponse().stream().filter(user -> user.getUserId() == userDetail.getUserId()).findFirst().get().getSkills());
                    List<LanguageResponse> languageList = getUserLanguage(userDetail.getUserId());
                    userDetail.setLanguages(languageList);
                }
                return userDetails;
            }
            if (languageIds != null) {
                String query=dbQueries.getUserDetailsByLanguageIds();
                SqlParameterSource parameters =new MapSqlParameterSource("languageIds",languageIds);
                userDetails = namedParameterJdbcTemplate.query(query,parameters, BeanPropertyRowMapper.newInstance(UserResponse.class));
            } else if (countryId != null) {
                userDetails = jdbcTemplate.query(dbQueries.getUserDetailsByCountryId(), BeanPropertyRowMapper.newInstance(UserResponse.class), countryId);
            } else if (userId != null) {
                userDetails = jdbcTemplate.query(dbQueries.getUserDetailsByUserId(), BeanPropertyRowMapper.newInstance(UserResponse.class), userId);
            } else if (email != null) {
                userDetails = jdbcTemplate.query(dbQueries.getUserDetailsByEmail(), BeanPropertyRowMapper.newInstance(UserResponse.class), email);
            } else {
                userDetails = jdbcTemplate.query(dbQueries.getUserDetails(), BeanPropertyRowMapper.newInstance(UserResponse.class));
            }

            userDetails.forEach(detail -> {
                Integer fetchUserId = detail.getUserId();
                List<LanguageResponse> languageList = getUserLanguage(fetchUserId);
                FlResponse<List<UserSkillsResponse>> skillList = userSkillClient.getUserSkills(fetchUserId);
                FlResponse<List<RatingResponse>> ratings = userProjectClient.getUserRatingsByUserId(fetchUserId);
                detail.setLanguages(languageList);
                if (!skillList.getResponse().isEmpty()) {
                    detail.setSkills(skillList.getResponse().get(0).getSkills());
                }

                if (!ratings.getResponse().isEmpty()) {
                    ratings.getResponse().forEach(rating ->
                            detail.getRatings().add(RatingResponse.builder().userId(rating.getUserId())
                                    .projectId(rating.getProjectId())
                                    .ratingDescription(rating.getRatingDescription())
                                    .rating(rating.getRating()).build()));
                }
            });
            return userDetails;
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

    public List<LanguageResponse> getUserLanguage(Integer userId) {
        return jdbcTemplate.query(dbQueries.getLanguagesByUserId(), BeanPropertyRowMapper.newInstance(LanguageResponse.class), userId);
    }
}
