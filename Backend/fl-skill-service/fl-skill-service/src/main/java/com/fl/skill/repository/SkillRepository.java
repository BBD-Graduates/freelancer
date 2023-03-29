package com.fl.skill.repository;

import com.fl.skill.model.Request.Skill;
import com.fl.skill.model.Response.CategorySkillList;
import com.fl.skill.model.Response.SkillRes;

import java.util.List;

public interface SkillRepository {

    String save(Skill skill);
    List<SkillRes> getAll();
    List<SkillRes> getById( int id);
    List<SkillRes> getByCategoryId( int id);
    String update(Skill skill, int id);
    String delete(int id);
    List<CategorySkillList> getAllCategorySkills();

}
