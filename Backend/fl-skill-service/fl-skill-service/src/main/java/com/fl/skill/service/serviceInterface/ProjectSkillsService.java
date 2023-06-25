package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.model.response.ProjectSkillsResponse;

import java.util.List;

public interface ProjectSkillsService {

    String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList)  ;
    List<ProjectSkillsResponse> getProjectSkills(Integer projectId,List<Integer> skillIds,Integer categoryId)  ;
}
