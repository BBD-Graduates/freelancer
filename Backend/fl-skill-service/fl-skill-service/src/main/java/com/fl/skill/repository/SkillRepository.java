package com.fl.skill.repository;

import com.fl.skill.model.Request.Skill;
import com.fl.skill.model.Response.SkillRes;

import java.util.List;

public interface SkillRepository {

    int save(Skill skill);
    List<SkillRes> getAll();
    List<SkillRes> getById( int id);
    List<SkillRes> getByCategoryId( int id);
    int update(Skill skill, int id);
    int delete(int id);

}
