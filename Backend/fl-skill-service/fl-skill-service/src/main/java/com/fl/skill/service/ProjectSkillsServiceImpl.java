package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.ProjectNotFoundException;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectSkillsServiceImpl implements ProjectSkillsService {
    
    private final JdbcTemplate jdbcTemplate;
    private final DbQueries dbQueries;

    @Override
    public String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList) throws ProjectNotFoundException {
        try {

            // List<ProjectSkillsReq> dataToInsert =
            // projectSkillReqList.stream().map(projectSkillsReq ->
            // ProjectSkillsReq.builder().
            // skillId(projectSkillsReq.getSkillId()).
            // projectId(projectSkillsReq.getProjectId()).build()).collect(Collectors.toList());
            // jdbcTemplate.batchUpdate(dbQueries.getAddProjectSkills(), dataToInsert);

            int insertStatus = 0;
            for (ProjectSkillsReq projectSkillsReq : projectSkillReqList) {
                insertStatus += jdbcTemplate.update(dbQueries.getAddProjectSkills(), projectSkillsReq.getProjectId(),
                        projectSkillsReq.getSkillId());
            }
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new ProjectNotFoundException("Error to insert project " + e);
        }

    }

    @Override
    public List<ProjectSkills> getallProjectSkills() throws ProjectNotFoundException {
        try {
            int size = 0;
            List<ProjectSkills> projectSkillList = getAllProjectId();
            if (!projectSkillList.isEmpty()) {
                size = projectSkillList.size();
                for (int i = 0; i < size; i++) {
                    ProjectSkills projectSkills = projectSkillList.get(i);
                    projectSkillsByProjectId(projectSkills.getProjectId()).forEach(projectSkillList::add);
                }
            }
            return projectSkillList.subList(size, projectSkillList.size());
        } catch (Exception e) {
            throw new ProjectNotFoundException("Error to fetch projects " + e);
        }
    }

    @Override
    public List<ProjectSkills> projectSkillsBySkillId(Integer skillId) throws ProjectNotFoundException {
        try {
            List<ProjectSkills> projectSkillList = new ArrayList<>();
            getProjectId(skillId).forEach(projectSkillList::add);
            List<ProjectSkills> skillRes = new ArrayList<>();
            if (!projectSkillList.isEmpty()) {
                for (int i = 0; i < projectSkillList.size(); i++) {
                    ProjectSkills projectSkills = projectSkillList.get(i);
                    projectSkillsByProjectId(projectSkills.getProjectId()).forEach(skillRes::add);
                }
            }
            return skillRes;
        } catch (Exception e) {
            throw new ProjectNotFoundException("Error to fetch projects " + e);
        }
    }

    @Override
    public List<ProjectSkills> projectSkillsByProjectId(Integer projectId) throws ProjectNotFoundException {
        try {
            return jdbcTemplate.query(dbQueries.getSelectProjectSkillsByProjectId(),
                    BeanPropertyRowMapper.newInstance(ProjectSkills.class),
                    projectId);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Error to get projects " + e);
        }

    }

    @Override
    public List<ProjectSkills> getAllProjectId() throws ProjectNotFoundException {
        return jdbcTemplate.query(dbQueries.getSelectAllProject(), BeanPropertyRowMapper.newInstance(ProjectSkills.class));
    }

    @Override
    public List<ProjectSkills> getProjectId(int id) throws ProjectNotFoundException {
        return jdbcTemplate.query(dbQueries.getSelectProjectByProjectId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class), id);

    }
}
