package com.fl.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String company;
    private String email;
    private String phNo;
    private Boolean isVerified;
    private String photoURL;
    private Date createdDate;
}
