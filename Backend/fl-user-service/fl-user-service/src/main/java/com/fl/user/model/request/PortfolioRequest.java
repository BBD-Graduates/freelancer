package com.fl.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioRequest {

    private Integer userId;
    private String title;
    private String description;
    private String imageURL;
}
