package com.fl.project.service.serviceInterface;

import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.ProjectRes;

import java.util.List;

public interface ProjectService {
    String save(Project project);
    List<ProjectRes> getProject(Integer ProjectId);
    String update(Project project, int projectId);
    String delete(int projectId);
}
