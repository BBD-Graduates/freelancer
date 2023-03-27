package com.fl.skill.service;

import com.fl.skill.model.Request.ProjectSkillsReq;
import com.fl.skill.model.Response.ProjectSkills;
import com.fl.skill.repository.ProjectSkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public List<ProjectSkills> getall() {
        return null;
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
