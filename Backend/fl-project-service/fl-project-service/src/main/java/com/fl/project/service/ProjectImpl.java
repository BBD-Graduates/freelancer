package com.fl.project.service;

import com.fl.project.feignClient.ProjectSkillService;
import com.fl.project.model.FlResponse;
import com.fl.project.model.Request.Project;
import com.fl.project.model.Response.ProjectRes;
import com.fl.project.model.Response.ProjectSkillsResponse;
import com.fl.project.model.Response.Skill;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fl.project.config.Constant.*;

@Service
@RequiredArgsConstructor
public class ProjectImpl implements ProjectService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    private final ProjectSkillService projectSkillService;

    @Override
    public String save(Project project) {
        int isInserted = jdbcTemplate.update(
                dbQueries.getAddProject(), project.getClientId(), project.getProjectName(),
                project.getProjectDescription(), project.getIsConfidential(), project.getPaymentTypeId(),
                project.getBidStartDate(), project.getBidEndDate(), project.getMinPrice(), project.getMaxPrice());
        if (isInserted > 0) {
            return INSERTED_SUCCESSFULLY;
        } else {
            return CANT_PROCESS_REQUEST;
        }
    }

    @Override
    public List<ProjectRes> getProject(Integer ProjectId) {
        List<ProjectRes> projects;
        try {
            if (ProjectId.equals(0)) {
                projects = jdbcTemplate.query(dbQueries.getSelectAllProject(),
                        BeanPropertyRowMapper.newInstance(ProjectRes.class));
            } else {
                projects = jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(),
                        BeanPropertyRowMapper.newInstance(ProjectRes.class), ProjectId);
            }
            if (!projects.isEmpty()) {
                projects.stream().forEach(project -> {
                    Integer projectid = project.getProjectId();
                    FlResponse<List<ProjectSkillsResponse>> skillList = projectSkillMicroservice(projectid);
                    List<Skill> skills = new ArrayList<>();
                    skillList.getResponse().stream().map(ProjectSkillsResponse::getSkills).forEach(x->{
                        for (Skill skill : x) {
                            skills.add(skill);
                        };
                    });
                    project.setSkills(skills);
                });
                return projects;
            } else
                return projects;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public String update(Project project, int projectId) {
        try {
            int isUpdated = jdbcTemplate.update(dbQueries.getUpdateProjectByProjectid(),
                    project.getProjectName(), project.getProjectDescription(), project.getPaymentTypeId(),
                    project.getMinPrice(), project.getMaxPrice(), projectId);
            if (isUpdated > 0)
                return UPDATED_SUCCESSFULLY;
            else
                return CANT_PROCESS_REQUEST;
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public String delete(int projectId) {
        try {
            int isDeleted = jdbcTemplate.update(dbQueries.getDeleteProjectByProjectid(), projectId);
            if (isDeleted > 0)
                return DELETED_SUCCESSFULLY;
            else
                return CANT_PROCESS_REQUEST;
        } catch (Exception ex) {
            throw ex;
        }
    }

    private FlResponse<List<ProjectSkillsResponse>> projectSkillMicroservice(Integer projectId) {
        return projectSkillService.getProjectSkillByProjectId(projectId);
    }
}
