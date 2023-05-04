package com.fl.project.feignClient;

import com.fl.project.model.FlResponse;
import com.fl.project.model.response.BidResponse;
import com.fl.project.model.response.ProjectSkillsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "FL-BID-SERVICE/bids")
@Service
public interface ProjectBidService {
    @GetMapping
    FlResponse<List<BidResponse>> getProjectBidByProjectId(@RequestParam("projectId") Integer projectId);
}
