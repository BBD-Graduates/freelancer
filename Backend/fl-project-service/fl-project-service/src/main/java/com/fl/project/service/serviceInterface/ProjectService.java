package com.fl.project.service.serviceInterface;

import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    String saveProject(ProjectRequest project);
    List<ProjectResponse> getProject(Integer ProjectId);
    String updateProject(ProjectRequest project, int projectId);
    String deleteProject(int projectId);
}
