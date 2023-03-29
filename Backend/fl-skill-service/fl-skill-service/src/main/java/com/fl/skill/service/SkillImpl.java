package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.CommonResponse;
import com.fl.skill.model.Request.Skill;
import com.fl.skill.model.Response.CategoryRes;
import com.fl.skill.model.Response.CategorySkillList;
import com.fl.skill.model.Response.SkillRes;
import com.fl.skill.queries.DbQueries;
import com.fl.skill.repository.CategoryRepository;
import com.fl.skill.repository.SkillRepository;
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
public class SkillImpl implements SkillRepository {

    @Autowired
    DbQueries dbQueries;

    @Autowired
    CategoryRepository catRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String save(Skill skill) {
        try {

            int insertStatus = jdbcTemplate.update(dbQueries.getAddSkill(), skill.getSkillName(), skill.getCategoryId());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SkillRes> getAll() {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills= jdbcTemplate.query(dbQueries.getSkills(), BeanPropertyRowMapper.newInstance(SkillRes.class));
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<SkillRes> getById(int id) {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills=  jdbcTemplate.query(dbQueries.getSkill(), BeanPropertyRowMapper.newInstance(SkillRes.class), id);
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<SkillRes> getByCategoryId(int id) {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills=  jdbcTemplate.query(dbQueries.getSkillsByCategory(),BeanPropertyRowMapper.newInstance(SkillRes.class), id);
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public String update(Skill skill, int id) {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateSkill(), skill.getSkillName(),skill.getCategoryId(), id);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(int id) {
        try {
            int removeStatus = jdbcTemplate.update(dbQueries.getRemoveSkill(), id);
            if (removeStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategorySkillList> getAllCategorySkills() {
        try {
            List<CategoryRes> category = new ArrayList<>();
            catRepo.getAll().forEach(category::add);
            List<CategorySkillList> list = new ArrayList<>();
            if(!category.isEmpty()){
                for(int i = 0;i<category.size();i++){
                    CategorySkillList cs;
                    List<SkillRes> skillRes = new ArrayList<>();

                    CategoryRes obj = category.get(i);
                    getByCategoryId(obj.getCategoryId()).forEach(skillRes::add);
                    cs=new CategorySkillList(obj.getCategoryId(),obj.getCategoryName(),obj.getLogoURl(),obj.isDeleted(),obj.getCreatedDate(),skillRes);
                    list.add(cs);
                }
            }
            return list;
        }
        catch (DataAccessException e) {
            throw e;
        }
    }
}

