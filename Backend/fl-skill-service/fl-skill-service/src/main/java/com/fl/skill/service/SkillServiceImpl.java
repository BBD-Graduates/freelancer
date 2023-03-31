package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.CategoryService;
import com.fl.skill.service.serviceInterface.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    DbQueries dbQueries;

    @Autowired
    CategoryService catRepo;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String insertSkills(Skill skill) {
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
    public List<SkillRes> getAllSkills() {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills= jdbcTemplate.query(dbQueries.getSkills(), BeanPropertyRowMapper.newInstance(SkillRes.class));
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<SkillRes> getSkillBySkillId(int id) {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills=  jdbcTemplate.query(dbQueries.getSkill(), BeanPropertyRowMapper.newInstance(SkillRes.class), id);
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<SkillRes> getSkillByCategoryId(int id) {

        try {
            List<SkillRes> skills=new ArrayList<>();
            skills=  jdbcTemplate.query(dbQueries.getSkillsByCategory(),BeanPropertyRowMapper.newInstance(SkillRes.class), id);
            return skills;
        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public String updateSkill(Skill skill, int id) {
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
    public String deleteSkill(int id) {
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
    public List<CategorySkills> getAllCategorySkills() {
        try {
            List<CategoryRes> category = new ArrayList<>();
            catRepo.getAllCategories().forEach(category::add);
            List<CategorySkills> list = new ArrayList<>();
            if(!category.isEmpty()){
                for(int i = 0;i<category.size();i++){
                    CategorySkills cs;
                    List<SkillRes> skillRes = new ArrayList<>();

                    CategoryRes obj = category.get(i);
                    getSkillByCategoryId(obj.getCategoryId()).forEach(skillRes::add);
                    cs=new CategorySkills(obj.getCategoryId(),obj.getCategoryName(),obj.getLogoURl(),obj.isDeleted(),obj.getCreatedDate(),skillRes);
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

