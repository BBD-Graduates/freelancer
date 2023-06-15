package com.fl.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationRequest {
    private Integer userId;
    private Integer cityId;
    private String pincode;
    private String address1;
    private String address2;

}
