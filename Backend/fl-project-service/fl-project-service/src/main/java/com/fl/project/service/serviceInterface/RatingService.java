package com.fl.project.service.serviceInterface;

import java.util.List;

import com.fl.project.model.request.RatingRequest;
import com.fl.project.model.response.RatingResponse;

public interface RatingService {
    String insertRating(RatingRequest rating);
    List<RatingResponse> getRatings(Integer ratingId,Integer userId,Integer ProjectId);
    String updateRating(Integer ratingId,RatingRequest rating);
    String deleteRating(Integer ratingId);
}
