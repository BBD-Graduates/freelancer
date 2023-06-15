package com.fl.user.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponse {
    private Integer portfolioId;
    private Integer userId;
    private String title;
    private String description;
    private String imageURL;
    private String createdDate;
}
