package com.fl.user.feignClient;

import com.fl.user.model.FlResponse;
import com.fl.user.model.response.UserSkillsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "FL-SKILL-SERVICE/user-skills")
@Service
public interface UserSkillClient {

    @GetMapping
    FlResponse<List<UserSkillsResponse>> getUserSkills(@RequestParam(name = "userId",required = false,defaultValue = "0") Integer userId);

    @GetMapping
    FlResponse<List<UserSkillsResponse>> getUserSkillBySkillId(@RequestParam(name = "skillId",required = false,defaultValue = "0") Integer skillId);

}
