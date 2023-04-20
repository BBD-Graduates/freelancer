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

    @Value("${db.queries.users.getUserDetailsByLanguageId}")
    private String userDetailsByLanguageId;

    @Value("${db.queries.userLanguages.insertUserLanguages}")
    private String adduserLanguages;
}
