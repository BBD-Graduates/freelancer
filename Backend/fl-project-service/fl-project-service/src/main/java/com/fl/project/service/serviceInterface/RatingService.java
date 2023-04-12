package com.fl.project.service.serviceInterface;

import java.util.List;

import com.fl.project.model.Request.RatingReq;
import com.fl.project.model.Response.RatingResponse;

public interface RatingService {
    String insertRating(RatingReq rating);
    List<RatingResponse> getRatings(Integer ratingId,Integer userId,Integer ProjectId);
    String updateRating(Integer ratingId,RatingReq rating);
    String deleteRating(Integer ratingId);
}
