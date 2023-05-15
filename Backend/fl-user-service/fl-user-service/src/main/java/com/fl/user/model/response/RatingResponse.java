package com.fl.user.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponse {
    private int userId;
    private int projectId;
    private String ratingDescription;
    private int rating;
}
