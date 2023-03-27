package com.fl.skill.model.Request;

import com.fl.skill.model.Response.ProjectSkills;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ProjectSkillsReq {
    private int projectId;
    private List<ProjectSkills> skills;
}
