package com.fl.project.service;

import com.fl.project.feignClient.ProjectBidService;
import com.fl.project.feignClient.ProjectSkillService;
import com.fl.project.model.FlResponse;
import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.response.BidResponse;
import com.fl.project.model.response.ProjectResponse;
import com.fl.project.model.response.ProjectSkillsResponse;
import com.fl.project.model.response.Skill;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fl.project.config.Constant.*;

@Service
@RequiredArgsConstructor
public class ProjectImpl implements ProjectService {

    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;
    private final ProjectSkillService projectSkillService;
    private final ProjectBidService projectBidService;

    @Override
    public String saveProject(ProjectRequest project) {
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
    public List<ProjectResponse> getProject(Integer ProjectId) {
        List<ProjectResponse> projects;
        try {
            if (ProjectId.equals(0)) {
                projects = jdbcTemplate.query(dbQueries.getSelectAllProject(),
                        BeanPropertyRowMapper.newInstance(ProjectResponse.class));
            } else {
                projects = jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(),
                        BeanPropertyRowMapper.newInstance(ProjectResponse.class), ProjectId);
            }
            if (!projects.isEmpty()) {
                projects.stream().forEach(project -> {
                    FlResponse<List<ProjectSkillsResponse>> skillList = projectSkillService.getProjectSkillByProjectId(project.getProjectId());
                    if(skillList.getResponse().size()>0){
                        project.setSkills(skillList.getResponse().get(0).getSkills());
                    }


                    FlResponse<List<BidResponse>> projectBidList = projectBidService.getProjectBidByProjectId(project.getProjectId());
                    if(projectBidList.getResponse().size()>0){
                        project.setBids(projectBidList.getResponse().stream().map(bidResponse -> BidResponse.builder()
                                .bidId(bidResponse.getBidId())
                                .projectId(bidResponse.getProjectId())
                                .freelancerId(bidResponse.getFreelancerId())
                                .amount(bidResponse.getAmount())
                                .description(bidResponse.getDescription())
                                .createdDate(bidResponse.getCreatedDate())
                                .build()).collect(Collectors.toList()));
                    }

                });
                return projects;
            } else
                return projects;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateProject(ProjectRequest project, int projectId) {
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
    public String deleteProject(int projectId) {
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
}
