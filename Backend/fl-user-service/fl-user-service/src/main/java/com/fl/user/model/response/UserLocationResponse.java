package com.fl.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationResponse {

    private Integer userId;
    private Integer cityId;
    private Integer stateId;
    private Integer countryId;
    private String pincode;
    private String address1;
    private String address2;
}
