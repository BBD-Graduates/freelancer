package com.fl.bid.client;

import com.fl.bid.model.FlResponse;
import com.fl.bid.model.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@FeignClient(url = "${service.props.webservices.fl-user-service.endpoint}" , name = "fl-user-service")
//@FeignClient(name = "FL-USER-SERVICE/users")
@Service
public interface BidUserClient {

    @GetMapping
    FlResponse<List<UserResponse>> getFreelancerById(@RequestParam("userId") Integer userId);

}
