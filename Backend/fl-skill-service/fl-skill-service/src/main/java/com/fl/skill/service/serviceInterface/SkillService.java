package com.fl.skill.service.serviceInterface;

import com.fl.skill.exceptions.CategoryNotFoundException;
import com.fl.skill.exceptions.SkillNotFoundException;
import com.fl.skill.model.request.Skill;
import com.fl.skill.model.response.CategorySkills;
import com.fl.skill.model.response.SkillRes;

import java.util.List;

public interface SkillService {

    String insertSkills(Skill skill) throws SkillNotFoundException;
    List<SkillRes> getSkills(Integer skillId,Integer categoryId) throws SkillNotFoundException;
    String updateSkill(Skill skill, int skillId) throws SkillNotFoundException;
    String deleteSkill(int skillId) throws SkillNotFoundException;
    List<CategorySkills> getAllCategorySkills() throws SkillNotFoundException,CategoryNotFoundException; 
}
