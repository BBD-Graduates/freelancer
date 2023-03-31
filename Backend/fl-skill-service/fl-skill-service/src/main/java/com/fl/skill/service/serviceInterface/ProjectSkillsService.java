package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;

import java.util.List;

public interface ProjectSkillsService {

    String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList);
    List<ProjectSkills> getallProjectSkills();
    List<ProjectSkills> projectSkillsBySkillId(Integer id);
    List<ProjectSkills> projectSkillsByProjectId(Integer id);
    List<ProjectSkills> getAllProjectId();
    List<ProjectSkills> getProjectId(int id);
}
