package com.fl.project.service;

import com.fl.project.feignClient.ProjectBidService;
import com.fl.project.feignClient.ProjectSkillService;
import com.fl.project.model.FlResponse;
import com.fl.project.model.request.ProjectRequest;
import com.fl.project.model.request.ProjectSkillsRequest;
import com.fl.project.model.response.BidResponse;
import com.fl.project.model.response.ProjectResponse;
import com.fl.project.model.response.ProjectSkillsResponse;
import com.fl.project.repository.DbQueries;
import com.fl.project.service.serviceInterface.ProjectService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.fl.project.config.Constant.*;
import static com.fl.project.config.ProjectStatus.APPROVED;

@Service
@RequiredArgsConstructor
public class ProjectImpl implements ProjectService {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final DbQueries dbQueries;
    private final ProjectSkillService projectSkillService;
    private final ProjectBidService projectBidService;

    @Override
    public String saveProject(ProjectRequest project) {
        try {
            KeyHolder identityValue = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(dbQueries.getAddProject(), Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, project.getClientId());
                    ps.setString(2, project.getProjectName());
                    ps.setString(3, project.getProjectDescription());
                    ps.setBoolean(4, project.getIsConfidential());
                    ps.setDate(5, new java.sql.Date(project.getBidStartDate().getTime()));
                    ps.setDate(6, new java.sql.Date(project.getBidEndDate().getTime()));
                    ps.setFloat(7, project.getMinPrice());
                    ps.setFloat(8, project.getMaxPrice());
                    return ps;
                }
            }, identityValue);
            Number projectId = identityValue.getKey().intValue();
            if (projectId.intValue() > 0) {
                List<ProjectSkillsRequest> projectskills = new ArrayList<>();

                project.getSkillIds().forEach(skillId -> {
                    projectskills.add(new ProjectSkillsRequest(projectId.intValue(), skillId));
                });
                FlResponse<String> status = projectSkillService.addProjectSkills(projectskills);
                if (Objects.equals(status.getResponse(), PROJECT_SKILLS + INSERTED_SUCCESSFULLY)) {
                    return PROJECT + INSERTED_SUCCESSFULLY;
                } else {
                    return PROJECT_SKILLS + INSERTION_FAILED;
                }

            } else {
                return CANT_PROCESS_REQUEST;
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<ProjectResponse> getProject(Integer projectId, List<Integer> skillIds, Integer categoryId, Integer clientId, Integer freelancerId, List<String> status) {
        List<ProjectResponse> projects;
        try {
            if (projectId.equals(0) && skillIds.isEmpty() && categoryId.equals(0) && clientId.equals(0) && freelancerId.equals(0) && status.isEmpty()){
                projects = jdbcTemplate.query(dbQueries.getSelectAllProject(),
                        BeanPropertyRowMapper.newInstance(ProjectResponse.class));
            } else if (!freelancerId.equals(0) && !status.isEmpty()) {
                FlResponse<List<BidResponse>> freelancerBids = projectBidService.getBidByFreelancerId(freelancerId, APPROVED.toString());
                List<Integer> bidProjectIds = new ArrayList<>();
                freelancerBids.getResponse().forEach(bidResponse -> {
                    bidProjectIds.add(bidResponse.getProjectId());
                });
                SqlParameterSource parameters = new MapSqlParameterSource()
                        .addValue("projectIds", bidProjectIds)
                        .addValue("status", status);
                projects = namedParameterJdbcTemplate.query(dbQueries.getAssignedProjectsByProjectIds(), parameters,
                        BeanPropertyRowMapper.newInstance(ProjectResponse.class));
            } else if (!skillIds.isEmpty()) {
                FlResponse<List<ProjectSkillsResponse>> skillList = projectSkillService.getProjectSkill(0,
                        skillIds, 0);
                if (!skillList.getResponse().isEmpty()) {
                    List<Integer> projectIds = skillList.getResponse().stream().map(ProjectSkillsResponse::getProjectId)
                            .toList();

                    String inSql = String.join(",", Collections.nCopies(projectIds.size(), "?"));

                    projects = jdbcTemplate.query(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                            PreparedStatement ps = con
                                    .prepareStatement(String.format(dbQueries.getSelectProjectByProjectIds(), inSql));
                            for (int i = 0; i < projectIds.size(); i++) {
                                ps.setObject(i + 1, projectIds.get(i));
                            }
                            return ps;
                        }
                    }, new RowMapperResultSetExtractor<>(BeanPropertyRowMapper.newInstance(ProjectResponse.class)));

                } else {
                    projects = jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(),
                            BeanPropertyRowMapper.newInstance(ProjectResponse.class), projectId);
                }
            } else if (!clientId.equals(0) && !status.isEmpty()) {
                SqlParameterSource parameters = new MapSqlParameterSource()
                        .addValue("clientId", clientId)
                        .addValue("status", status);
                projects = namedParameterJdbcTemplate.query(dbQueries.getSelectProjectsByClientIdAndStatus(), parameters, BeanPropertyRowMapper.newInstance(ProjectResponse.class));
            } else if (!categoryId.equals(0)) {
                FlResponse<List<ProjectSkillsResponse>> skillList = projectSkillService.getProjectSkill(0,
                        null, categoryId);
                List<Integer> projectIds;
                if (!skillList.getResponse().isEmpty()) {
                    projectIds = skillList.getResponse().stream().map(project -> project.getProjectId()).toList();

                    String inSql = String.join(",", Collections.nCopies(projectIds.size(), "?"));

                    projects = jdbcTemplate.query(new PreparedStatementCreator() {
                        @Override
                        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                            PreparedStatement ps = con
                                    .prepareStatement(String.format(dbQueries.getSelectProjectByProjectIds(), inSql));
                            for (int i = 0; i < projectIds.size(); i++) {
                                ps.setObject(i + 1, projectIds.get(i));
                            }
                            return ps;
                        }
                    }, new RowMapperResultSetExtractor<>(BeanPropertyRowMapper.newInstance(ProjectResponse.class)));

                } else {
                    projects = jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(),
                            BeanPropertyRowMapper.newInstance(ProjectResponse.class), projectId);
                }
            } else {
                projects = jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(),
                        BeanPropertyRowMapper.newInstance(ProjectResponse.class), projectId);
            }
            if (!projects.isEmpty()) {
                projects.forEach(project -> {
                    FlResponse<List<ProjectSkillsResponse>> skillList = projectSkillService
                            .getProjectSkill(project.getProjectId(), null, 0);
                    if (!skillList.getResponse().isEmpty()) {
                        project.setSkills(skillList.getResponse().get(0).getSkills());
                    }

                    FlResponse<List<BidResponse>> projectBidList = projectBidService
                            .getProjectBidByProjectId(project.getProjectId());
                    if (!projectBidList.getResponse().isEmpty()) {
                        project.setBids(projectBidList.getResponse().stream().map(bidResponse -> BidResponse.builder()
                                .bidId(bidResponse.getBidId())
                                .projectId(bidResponse.getProjectId())
                                .freelancerId(bidResponse.getFreelancerId())
                                .amount(bidResponse.getAmount())
                                .description(bidResponse.getDescription())
                                .createdDate(bidResponse.getCreatedDate())
                                .build()).toList());
                    }
                });
            }
            return projects;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public String updateProject(ProjectRequest project, Integer projectId, String projectStatus) {
        try {
            int isUpdated;
            if (projectStatus != null && projectId != null) {
                isUpdated = jdbcTemplate.update(dbQueries.getUpdateProjectStatus(),projectStatus,projectId);
            } else {
                isUpdated = jdbcTemplate.update(dbQueries.getUpdateProjectByProjectId(),
                        project.getProjectName(), project.getProjectDescription(),
                        project.getMinPrice(), project.getMaxPrice(), projectId);
            }
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
            int isDeleted = jdbcTemplate.update(dbQueries.getDeleteProjectByProjectId(), projectId);
            if (isDeleted > 0)
                return DELETED_SUCCESSFULLY;
            else
                return CANT_PROCESS_REQUEST;
        } catch (Exception ex) {
            throw ex;
        }
    }
    @Override
    public List<ProjectResponse> getAllStatusProjects() {
        return   jdbcTemplate.query(dbQueries.getAllStatusProjects(),
                BeanPropertyRowMapper.newInstance(ProjectResponse.class));
    }

}