package com.fl.skill.repository;

import com.fl.skill.model.Request.ProjectSkillsReq;
import com.fl.skill.model.Response.ProjectSkills;

import java.util.List;

public interface ProjectSkillsRepository {

    int save(ProjectSkillsReq projectSkillsReq);

    List<ProjectSkills> getall();
    List<ProjectSkills> getByProjectId(int id);

    List<ProjectSkills> getAllProjectId();

    List<ProjectSkills> getProjectId(int id);
}
