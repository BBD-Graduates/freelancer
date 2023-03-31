package com.fl.skill.service.serviceInterface;

import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;

import java.util.List;

public interface SkillService {

    String insertSkills(Skill skill);
    List<SkillRes> getAllSkills();
    List<SkillRes> getSkillBySkillId(int id);
    List<SkillRes> getSkillByCategoryId(int id);
    String updateSkill(Skill skill, int id);
    String deleteSkill(int id);
    List<CategorySkills> getAllCategorySkills();
}
