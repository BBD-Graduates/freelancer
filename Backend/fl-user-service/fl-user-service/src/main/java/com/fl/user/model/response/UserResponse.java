package com.fl.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String headLine;
    private String summary;
    private String company;
    private String email;
    private String phNo;
    private Boolean isVerified;
    private String photoUrl;
    private String userRole;
    private Date createdDate;
    private String countryId;    
    private String countryName;
    private String stateId;
    private String stateName;
    private String cityId;
    private String cityName;    
    private String pincode;
    private String address1;
    private String address2;

    @Builder.Default
    List<LanguageResponse> languages = new ArrayList<>();
    @Builder.Default
    List<SkillResponse> skills = new ArrayList<>();
    @Builder.Default
    List<RatingResponse> ratings=new ArrayList<>();

}
