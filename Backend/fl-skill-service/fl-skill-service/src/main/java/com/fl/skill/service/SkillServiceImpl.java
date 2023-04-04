package com.fl.skill.service;

import com.fl.skill.config.Constant;
import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.exceptions.SkillNotFoundException;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategoryRes;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;
import com.fl.skill.repository.DbQueries;
import com.fl.skill.service.serviceInterface.CategoryService;
import com.fl.skill.service.serviceInterface.SkillService;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    
    private final DbQueries dbQueries;
    private final CategoryService catService;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public String insertSkills(Skill skill) throws SkillNotFoundException {
        try {

            int insertStatus = jdbcTemplate.update(dbQueries.getAddSkill(), skill.getSkillName(),
                    skill.getCategoryId());
            if (insertStatus > 0) {
                return Constant.INSERTED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new SkillNotFoundException("Error inserting Skills " + e);
        }
    }

    @Override
    public List<SkillRes> getSkills(Integer skillId, Integer categoryId) throws SkillNotFoundException {

        try {
            if (!skillId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getSkillBySkillId(),
                BeanPropertyRowMapper.newInstance(SkillRes.class), categoryId);
            } else if (!categoryId.equals(0)) {
                return jdbcTemplate.query(dbQueries.getSkillByCategoryId(), BeanPropertyRowMapper.newInstance(SkillRes.class), skillId);
            } else {
                return jdbcTemplate.query(dbQueries.getAllSkills(), BeanPropertyRowMapper.newInstance(SkillRes.class));
            }
        } catch (Exception e) {
            throw new SkillNotFoundException("Error fetching Skills " + e);
        }
    }

    @Override
    public String updateSkill(Skill skill, int skillId) throws SkillNotFoundException {
        try {
            int updateStatus = jdbcTemplate.update(dbQueries.getUpdateSkill(), skill.getSkillName(),
                    skill.getCategoryId(), skillId);
            if (updateStatus > 0) {
                return Constant.UPDATED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new SkillNotFoundException("Error updating Skill " + e);
        }
    }

    @Override
    public String deleteSkill(int skillId) throws SkillNotFoundException {
        try {
            int removeStatus = jdbcTemplate.update(dbQueries.getRemoveSkill(), skillId);
            if (removeStatus > 0) {
                return Constant.DELETED_SUCCESSFULLY;
            } else {
                return Constant.CANT_PROCESS_REQUEST;
            }
        } catch (Exception e) {
            throw new SkillNotFoundException("Error deleting skill " + e);
        }
    }

    @Override
    public List<CategorySkills> getAllCategorySkills() throws SkillNotFoundException, CategoryNotFoundException {
        try {
            List<CategoryRes> categoryList = catService.getCategories(0);
            List<CategorySkills> categorySkillList = new ArrayList<>();
            if (!categoryList.isEmpty()) {
                for (CategoryRes category : categoryList) {
                    List<SkillRes> skillRes = getSkills(0,category.getCategoryId());
                    CategorySkills categorySkills = new CategorySkills(category.getCategoryId(), category.getCategoryName(), category.getLogoURl(),
                    category.isDeleted(), category.getCreatedDate(), skillRes);
                    categorySkillList.add(categorySkills);
                }
            }
            return categorySkillList;
        } catch (DataAccessException e) {
            throw new SkillNotFoundException("Error fetching CategorySkills " + e);
        }
    }
}
