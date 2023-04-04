package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.ProjectNotFoundException;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;

import lombok.RequiredArgsConstructor;

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
            int insertStatus = 0;
            for (ProjectSkillsReq projectSkillsReq : projectSkillReqList) {
                insertStatus += jdbcTemplate.update(dbQueries.getAddProjectSkill(), projectSkillsReq.getProjectId(),
                        projectSkillsReq.getSkillId());
            }
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new ProjectNotFoundException("Error inserting project skills " + e);
        }

    }

    @Override
    public List<ProjectSkills> getAllProjectSkills(Integer projectId) throws ProjectNotFoundException {
        try {
            if(!projectId.equals(0))
            {
                List<ProjectSkills> projectSkillList = getProjectId(projectId);
                List<ProjectSkills> skillRes = new ArrayList<>();
                if (!projectSkillList.isEmpty()) {
                    for (int i = 0; i < projectSkillList.size(); i++) {
                        ProjectSkills projectSkills = projectSkillList.get(i);
                        projectSkillsByProjectId(projectSkills.getProjectId()).forEach(skillRes::add);
                    }
                }
                return skillRes;
            }
            else {
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
            }

        } catch (Exception e) {
            throw new ProjectNotFoundException("Error fetching project skills " + e);
        }
    }

    @Override
    public List<ProjectSkills> projectSkillsByProjectId(Integer projectId) throws ProjectNotFoundException {

            return jdbcTemplate.query(dbQueries.getProjectSkillsByProjectId(),
                    BeanPropertyRowMapper.newInstance(ProjectSkills.class),
                    projectId);
    }

    @Override
    public List<ProjectSkills> getAllProjectId() throws ProjectNotFoundException {
        return jdbcTemplate.query(dbQueries.getAllProjectId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class));
    }

    @Override
    public List<ProjectSkills> getProjectId(int projectId) throws ProjectNotFoundException {
        return jdbcTemplate.query(dbQueries.getProjectId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class), projectId);

    }
}
