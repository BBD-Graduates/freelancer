package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.UserSkillsReq;
import com.fl.skill.model.response.UserSkills;
import com.fl.skill.model.response.UserSkillsResponse;

import java.util.List;

public interface UserSkillsService {

    String insertUserSkills(List<UserSkillsReq> userSkillReqList)  ;
    List<UserSkillsResponse> getUserSkills(Integer userId,List<Integer> skillIds, Integer categoryId)  ;
}
