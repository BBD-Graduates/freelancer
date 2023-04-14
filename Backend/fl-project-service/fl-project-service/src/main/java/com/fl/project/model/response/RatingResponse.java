package com.fl.project.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private int userId;
    private int projectId;
    private String ratingDescription;
    private int rating;
}
