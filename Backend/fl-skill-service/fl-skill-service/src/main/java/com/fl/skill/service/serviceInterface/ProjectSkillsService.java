package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.ProjectNotFoundException;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;

import java.util.List;

public interface ProjectSkillsService {

    String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList) throws ProjectNotFoundException;
    List<ProjectSkills> getallProjectSkills() throws ProjectNotFoundException;
    List<ProjectSkills> projectSkillsBySkillId(Integer skillId) throws ProjectNotFoundException;
    List<ProjectSkills> projectSkillsByProjectId(Integer projectId) throws ProjectNotFoundException;
    List<ProjectSkills> getAllProjectId() throws ProjectNotFoundException;
    List<ProjectSkills> getProjectId(int id) throws ProjectNotFoundException;
}
