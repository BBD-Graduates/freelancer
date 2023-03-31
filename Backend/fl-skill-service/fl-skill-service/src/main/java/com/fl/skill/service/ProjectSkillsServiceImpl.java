package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.ProjectSkillsReq;
import com.fl.skill.model.response.ProjectSkills;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.ProjectSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectSkillsServiceImpl implements ProjectSkillsService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DbQueries dbQueries;

    @Override
    public String insertProjectSkills(List<ProjectSkillsReq> projectSkillReqList) {
        try {

//            List<ProjectSkillsReq> dataToInsert = projectSkillReqList.stream().map(projectSkillsReq -> ProjectSkillsReq.builder().
//                    skillId(projectSkillsReq.getSkillId()).
//                    projectId(projectSkillsReq.getProjectId()).build()).collect(Collectors.toList());
//            jdbcTemplate.batchUpdate(dbQueries.getAddProjectSkills(), dataToInsert);

            int insertStatus=0;
            for(int i=0;i<projectSkillReqList.size();i++)
            {
                ProjectSkillsReq projectSkillsReq=new ProjectSkillsReq();
                ProjectSkillsReq obj=projectSkillReqList.get(i);
                projectSkillsReq.setProjectId(obj.getProjectId());
                projectSkillsReq.setSkillId(obj.getSkillId());
                insertStatus = jdbcTemplate.update(dbQueries.getAddProjectSkills(),projectSkillsReq.getProjectId(),projectSkillsReq.getSkillId());
            }
            if (insertStatus > 0)
            {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ProjectSkills> getallProjectSkills() {
        try {
            int size=0;
            List<ProjectSkills> projects=new ArrayList<>();
            projects= getAllProjectId();
            if(!projects.isEmpty())
            {
                 size=projects.size();
                for(int i=0;i<size;i++)
                {
                    ProjectSkills obj = projects.get(i);
                    projectSkillsByProjectId(obj.getProjectId()).forEach(projects::add);

                }
            }
            return projects.subList(size,projects.size());
        }
        catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ProjectSkills> projectSkillsBySkillId(Integer id) {
        try {
            List<ProjectSkills> projects = new ArrayList<>();
            getProjectId(id).forEach(projects::add);
            List<ProjectSkills> skillRes=new ArrayList<>();
            if(!projects.isEmpty())
            {
                for(int i=0;i<projects.size();i++)
                {
                    ProjectSkills obj = projects.get(i);
                    projectSkillsByProjectId(obj.getProjectId()).forEach(skillRes::add);
                }
            }
            return skillRes;
        }
        catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ProjectSkills> projectSkillsByProjectId(Integer id) {
        return jdbcTemplate.query(dbQueries.getProjectSkills(), BeanPropertyRowMapper.newInstance(ProjectSkills.class), id);
    }

    @Override
    public List<ProjectSkills> getAllProjectId() {
        return jdbcTemplate.query(dbQueries.getAllProjectId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class));
    }

    @Override
    public List<ProjectSkills> getProjectId(int id) {
        return jdbcTemplate.query(dbQueries.getProjectId(), BeanPropertyRowMapper.newInstance(ProjectSkills.class), id);

    }
}
