package com.fl.bid.client;

import com.fl.bid.model.FlResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${service.props.webservices.fl-project-service.endpoint}" , name = "fl-project-service")
//@FeignClient(name = "FL-PROJECT-SERVICE/projects")
@Service
public interface BidProjectClient {
    @PutMapping
    FlResponse<String> updateProjectStatus(@RequestParam("projectId") Integer projectId,
                                           @RequestParam("projectStatus") String projectStatus);
}
