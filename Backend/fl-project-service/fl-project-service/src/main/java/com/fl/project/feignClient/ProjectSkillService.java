package com.fl.project.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fl.project.model.FlResponse;
import com.fl.project.model.response.ProjectSkillsResponse;
@FeignClient(url = "${service.props.webservices.fl-skill-service.endpoint}" , name = "fl-skill-service")
//@FeignClient(name = "FL-SKILL-SERVICE/project-skills")
@Service
public interface ProjectSkillService {
    @GetMapping
    FlResponse<List<ProjectSkillsResponse>> getProjectSkill(@RequestParam("projectId") Integer projectId,@RequestParam("skillId") Integer skillId,@RequestParam("categoryId") Integer categoryId);
}
