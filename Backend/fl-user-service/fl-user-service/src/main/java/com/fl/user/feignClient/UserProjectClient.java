package com.fl.user.feignClient;

import com.fl.user.model.FlResponse;
import com.fl.user.model.response.RatingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${service.props.webservices.fl-project-service.endpoint}" , name = "fl-project-service")
// @FeignClient(name = "FL-PROJECT-SERVICE/projects/rating")
@Service
public interface UserProjectClient {
    @GetMapping
    FlResponse<List<RatingResponse>>  getUserRatingsByUserId(@RequestParam("userId") Integer userId);
}
