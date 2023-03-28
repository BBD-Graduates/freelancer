package com.fl.skill.service;

import com.fl.skill.model.Request.Skill;
import com.fl.skill.model.Response.SkillRes;
import com.fl.skill.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SkillImpl implements SkillRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int save(Skill skill) {
        String qry = "Insert into skills(SkillName,CategoryId) values(?,?)";
        return jdbcTemplate.update(qry,skill.getSkillName(),skill.getCategoryId());
    }

    @Override
    public List<SkillRes> getAll() {
        return jdbcTemplate.query("Select * from skills",BeanPropertyRowMapper.newInstance(SkillRes.class));
    }

    @Override
    public List<SkillRes> getById(int id) {
        return jdbcTemplate.query("select * from skills where SkillId = ? ",BeanPropertyRowMapper.newInstance(SkillRes.class),id);
    }

    @Override
    public List<SkillRes> getByCategoryId(int id) {
        return jdbcTemplate.query("select * from skills where CategoryId=?"
                ,BeanPropertyRowMapper.newInstance(SkillRes.class),id);
    }

    @Override
    public int update(Skill skill, int id) {
        String updateQuery = "update skills set SkillName = ? where SkillId= ?";

        // return jdbcTemplate.update("update skills set SkillName = ? where SkillId= ? "
        //         ,new Object[]{skill.getSkillName(),id});
        return jdbcTemplate.update(updateQuery,skill.getSkillName(),id);
    }

    @Override
    public int delete(int id) {

        return jdbcTemplate.update("delete from skills where SkillId = ?",id);
    }
}
