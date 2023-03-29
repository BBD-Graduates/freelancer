package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.ProjectSkillsReq;
import com.fl.skill.model.Response.ProjectSkillList;
import com.fl.skill.model.Response.ProjectSkills;
import com.fl.skill.repository.ProjectSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProjectSkillsImpl implements ProjectSkillsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int save(ProjectSkillsReq projectSkillsReq) {
        return 0;
    }

    @Override
    public List<ProjectSkillList> getall() {
        try {
            List<ProjectSkills> projects = new ArrayList<>();
            getAllProjectId().forEach(projects::add);
            List<ProjectSkillList> list = new ArrayList<>();
            if(!projects.isEmpty())
            {
                for(int i=0;i<projects.size();i++)
                {
                    ProjectSkillList ps;
                    List<ProjectSkills> skillRes=new ArrayList<>();
                    ProjectSkills obj = projects.get(i);
                    getByProjectId(obj.getProjectId()).forEach(skillRes::add);
                    ps=new ProjectSkillList(obj.getProjectId(), skillRes);
                    list.add(ps);
                }

            }
            return list;
        }
        catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<ProjectSkills> getByProjectId(int id) {
        return jdbcTemplate.query("select * from projectskills where ProjectId=?",BeanPropertyRowMapper.newInstance(ProjectSkills.class),id);
    }

    @Override
    public List<ProjectSkills> getAllProjectId() {
        return jdbcTemplate.query("select  DISTINCT(ProjectId) from projectskills",BeanPropertyRowMapper.newInstance(ProjectSkills.class));
    }

    @Override
    public List<ProjectSkills> getProjectId( int id) {
        return jdbcTemplate.query("select  DISTINCT(ProjectId) from projectskills where ProjectId=?",BeanPropertyRowMapper.newInstance(ProjectSkills.class),id);

    }
}
