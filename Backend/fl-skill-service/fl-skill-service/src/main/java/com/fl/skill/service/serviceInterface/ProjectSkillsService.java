package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.ProjectNotFoundException;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;

import java.util.List;

public interface ProjectSkillsService {

    String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList) throws ProjectNotFoundException;
    List<ProjectSkills> getAllProjectSkills(Integer projectId) throws ProjectNotFoundException;

    List<ProjectSkills> projectSkillsByProjectId(Integer projectId) throws ProjectNotFoundException;

    List<ProjectSkills> getAllProjectId() throws ProjectNotFoundException;
    List<ProjectSkills> getProjectId(int projectId) throws ProjectNotFoundException;
}
