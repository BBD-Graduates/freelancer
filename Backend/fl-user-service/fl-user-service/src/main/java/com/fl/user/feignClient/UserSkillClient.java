package com.fl.user.feignClient;

import com.fl.user.model.FlResponse;
import com.fl.user.model.response.UserSkillsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${service.props.webservices.fl-skill-service.endpoint}" , name = "fl-skill-service")
// @FeignClient(name = "FL-SKILL-SERVICE/user-skills")
@Service
public interface UserSkillClient {

    @GetMapping
    FlResponse<List<UserSkillsResponse>> getUserSkills(@RequestParam(name = "userId",required = false,defaultValue = "0") Integer userId);

    @GetMapping
    FlResponse<List<UserSkillsResponse>> getUserSkillBySkillId(@RequestParam(name = "skillIds",required = false,defaultValue = "0")  List<Integer> skillIds);

    @GetMapping
    FlResponse<List<UserSkillsResponse>> getUserSkillByCategoryId(@RequestParam(name = "categoryId",required = false,defaultValue = "0") Integer categoryId);

}
