package com.fl.user.repository;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Data
@Component
@RefreshScope
public class DbQueries {
    @Value("${db.queries.users.insertUser}")
    private String addUser;

    @Value("${db.queries.users.updateUser}")
    private String updateUser;

    @Value("${db.queries.users.getUserDetails}")
    private String userDetails;

    @Value("${db.queries.users.getUserDetailsByLanguageIds}")
    private String userDetailsByLanguageIds;

    @Value("${db.queries.users.getUserDetailsByCountryId}")
    private String userDetailsByCountryId;

    @Value("${db.queries.users.getUserDetailsByUserId}")
    private String userDetailsByUserId;

    @Value("${db.queries.users.getUserDetailsByEmail}")
    private String userDetailsByEmail;

    @Value("${db.queries.users.getUserDetailsInUserIds}")
    private String userDetailsInUserIds;

    @Value("${db.queries.users.getLanguagesByUserId}")
    private String languagesByUserId;

    @Value("${db.queries.userLanguages.insertUserLanguages}")
    private String adduserLanguages;
}
