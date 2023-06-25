package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.model.response.ProjectSkillsResponse;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectSkillsServiceImpl implements ProjectSkillsService {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList) {
        try {
            int[] insertStatus = jdbcTemplate.batchUpdate(dbQueries.getAddProjectSkill(),
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int row) throws SQLException {
                            ps.setInt(1,projectSkillReqList.get(row).getProjectId());
                            ps.setInt(2,projectSkillReqList.get(row).getSkillId());
                        }
                        @Override
                        public int getBatchSize() {
                            return projectSkillReqList.size() ;
                        }
                    });

            if (insertStatus.length > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<ProjectSkillsResponse> getProjectSkills(Integer projectId,List<Integer> skillIds,Integer categoryId) {

        try {
            List<ProjectSkillsResponse> projectSkillDetails = new ArrayList<>();
            String query;
            List<ProjectSkills> projectSkills;
            if (!projectId.equals(0)) {
                query = dbQueries.getProjectSkillDetailsByProjectId();
                projectSkills = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProjectSkills.class), projectId);
            } else if (skillIds != null) {
                SqlParameterSource parameters=new MapSqlParameterSource("skillId",skillIds);
                projectSkills = namedParameterJdbcTemplate.query(dbQueries.getProjectSkillDetailsBySkillId(),parameters, BeanPropertyRowMapper.newInstance(ProjectSkills.class));
            } else if (!categoryId.equals(0)) {
                projectSkills = jdbcTemplate.query(dbQueries.getProjectSkillDetailsByCategoryId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class), categoryId);
            } else {
                query = dbQueries.getProjectSkillDetails();
                projectSkills = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProjectSkills.class));
            }
            List<Integer> projectIdList = projectSkills.stream().map(ProjectSkills::getProjectId).distinct().collect(Collectors.toList());
            for (Integer fetchProjectId : projectIdList) {
                ProjectSkillsResponse projectSkillsResponse = new ProjectSkillsResponse();
                projectSkillsResponse.setProjectId(fetchProjectId);
                projectSkills.stream().filter(projectSkill -> projectSkill.getProjectId() == fetchProjectId)
                        .forEach(projectSkill -> projectSkillsResponse.getSkills()
                        .add(SkillRes.builder().skillId(projectSkill.getSkillId()).skillName(projectSkill.getSkillName()).categoryId(projectSkill.getCategoryId())
                                .build()));
                projectSkillDetails.add(projectSkillsResponse);
            }
            return projectSkillDetails;


        } catch (Exception e) {
            throw e;
        }
    }

}
