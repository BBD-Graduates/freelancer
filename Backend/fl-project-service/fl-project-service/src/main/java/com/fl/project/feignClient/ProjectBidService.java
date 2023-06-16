package com.fl.project.feignClient;

import com.fl.project.model.FlResponse;
import com.fl.project.model.response.BidResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(url = "${service.props.webservices.fl-bid-service.endpoint}" , name = "fl-bid-service")
// @FeignClient(name = "FL-BID-SERVICE/api/bids")
@Service
public interface ProjectBidService {
    @GetMapping
    FlResponse<List<BidResponse>> getProjectBidByProjectId(@RequestParam("projectId") Integer projectId);

    @GetMapping
    FlResponse<List<BidResponse>> getBidByFreelancerId(@RequestParam("freelancerId") Integer freelancerId,
                                                       @RequestParam("status") String status);

}
