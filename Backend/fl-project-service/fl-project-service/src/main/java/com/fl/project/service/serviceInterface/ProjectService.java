package com.fl.project.service.serviceInterface;

import com.fl.project.config.ProjectStatus;
import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    String saveProject(ProjectRequest project);
    List<ProjectResponse> getProject(Integer projectId, List<Integer> skillIds, Integer categoryId,Integer clientId, Integer freelancerId,List<String> status);
    String updateProject(ProjectRequest project, Integer projectId, String projectStatus);
    String deleteProject(int projectId);
    List<ProjectResponse> getAllStatusProjects();
}
