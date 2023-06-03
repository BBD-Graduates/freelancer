package com.fl.project.service.serviceInterface;

import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    String saveProject(ProjectRequest project);
    List<ProjectResponse> getProject(Integer projectId,Integer skillId,Integer categoryId,Integer clientId,List<String> status);
    String updateProject(ProjectRequest project, int projectId);
    String deleteProject(int projectId);
}
