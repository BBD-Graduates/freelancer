package com.fl.project.repository;

import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.ProjectRes;

import java.util.List;

public interface ProjectRepository {
    int save(Project project);
    List<ProjectRes> getAll();
    List<ProjectRes> getById(int id);
    int update(Project project, int id);
    int delete(int id);



}
